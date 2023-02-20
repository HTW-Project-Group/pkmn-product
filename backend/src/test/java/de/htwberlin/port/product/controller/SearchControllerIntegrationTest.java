package de.htwberlin.port.product.controller;

import static java.util.Map.entry;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.appservice.dto.ProductFactory;
import de.htwberlin.core.appservice.mapper.AttributeMapper;
import de.htwberlin.core.appservice.mapper.IProductMapperImpl;
import de.htwberlin.core.domain.repository.IProductRepository;
import de.htwberlin.core.domain.repository.ProductInMemoryRepository;
import de.htwberlin.core.domain.service.IPokemonService;
import de.htwberlin.core.domain.service.IProductService;
import de.htwberlin.core.domain.service.ISearchService;
import de.htwberlin.core.domain.service.impl.PokemonService;
import de.htwberlin.core.domain.service.impl.ProductService;
import de.htwberlin.core.domain.service.impl.SearchService;
import de.htwberlin.port.adapter.PokemonApiClient;
import de.htwberlin.port.annotation.IntegrationTest;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@IntegrationTest
class SearchControllerIntegrationTest {

  private MockMvc mvc;

  @MockBean private IProductService productService;
  @MockBean private IPokemonService pokemonService;
  @MockBean private ISearchService searchService;
  @MockBean private IProductRepository repository;
  @MockBean private SearchController controller;

  @BeforeEach
  void setUp() {
    // given
    var productMapper = new IProductMapperImpl();
    var attributeMapper = new AttributeMapper<ProductDto>();
    repository = new ProductInMemoryRepository();
    productService = new ProductService(repository, productMapper, attributeMapper);
    pokemonService = new PokemonService(new PokemonApiClient());
    searchService = new SearchService(productService, pokemonService);
    controller = new SearchController(searchService);

    Map.ofEntries(
            entry("base1-46", "Charmander"),
            entry("base1-24", "Charmeleon"),
            entry("base1-4", "Charizard"),
            entry("base1-44", "Bulbasaur"),
            entry("base1-30", "Ivysaur"),
            entry("base1-15", "Venusaur"))
        .forEach(
            (pId, pName) ->
                productService.createProduct(
                    ProductFactory.simpleProduct().pokemonId(pId).name(pName).build()));

    mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void shouldReturnCorrectProductsOnSearch() throws Exception {
    // given
    var search = "name:Char";

    // when
    mvc.perform(get("/v1/product/search/" + search))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(content().string(containsString("\"name\":\"Charmander\"")))
        .andExpect(content().string(containsString("\"name\":\"Charmeleon\"")))
        .andExpect(content().string(containsString("\"name\":\"Charizard\"")));
  }

  @Test
  void shouldReturnCorrectProductsOnSearchWithMultipleSearchParams() throws Exception {
    // given
    var search = "name:Charmander;types:Fire";

    // when
    mvc.perform(get("/v1/product/search/" + search))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(content().string(containsString("\"name\":\"Charmander\"")));
  }

  @Test
  void shouldReturnCorrectAmountOfProductsWithPageSize() throws Exception {
    // given
    var search = "name:Char";
    var page = 1;
    var pageSize = 2;

    // when
    mvc.perform(get("/v1/product/search/" + search + "/" + page + "/" + pageSize))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void shouldIgnoreBadSearchParams() throws Exception {
    // given
    var search = "garbage 123";

    // when
    mvc.perform(get("/v1/product/search/" + search))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(0)));
  }
}
