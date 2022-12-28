package de.htwberlin.core.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import de.htwberlin.core.appservice.dto.IProductMapperImpl;
import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.appservice.dto.ProductFactory;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.core.domain.repository.ProductInMemoryRepository;
import de.htwberlin.core.domain.service.impl.PokemonService;
import de.htwberlin.core.domain.service.impl.ProductService;
import de.htwberlin.core.domain.service.impl.SearchService;
import de.htwberlin.port.adapter.AttributeAdapter;
import de.htwberlin.port.adapter.PokemonApiClient;
import de.htwberlin.port.exception.InvalidSearchException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchServiceTest {

  private ISearchService searchService;

  @BeforeEach
  void setUp() {
    var productMapper = new IProductMapperImpl();
    var attributeAdapter = new AttributeAdapter<ProductDto>();
    var productRepository = new ProductInMemoryRepository();

    var products =
        List.of(
            productMapper.toEntity(
                ProductFactory.simpleProduct().name("Pikachu").pokemonId("basep-1").build()),
            productMapper.toEntity(
                ProductFactory.simpleProduct().name("Pikachu").pokemonId("basep-4").build()),
            productMapper.toEntity(
                ProductFactory.simpleProduct().name("Charizard").pokemonId("base1-4").build()));
    productRepository.saveAll(products);

    var productService = new ProductService(productRepository, productMapper, attributeAdapter);

    var apiClient = new PokemonApiClient();
    var pokemonService = new PokemonService(apiClient);

    searchService = new SearchService(productService, pokemonService);
  }

  @Test
  void shouldFindProductsByNameParam() {
    // given
    var search = SearchParam.builder().param("name").value("Pikachu").build();

    // when
    var result = searchService.searchForProducts(search);

    // then
    assertThat(result).hasSize(2);
    assertThat(result)
        .extracting(ProductDto::getPokemonId)
        .containsExactlyInAnyOrder("basep-1", "basep-4");
    assertThat(result.get(0).getName().toLowerCase()).contains("pikachu");
    assertThat(result.get(1).getName().toLowerCase()).contains("pikachu");
  }

  @Test
  void shouldIgnoreCaseOnSearch() {
    // given
    var search = SearchParam.builder().param("name").value("pIKaChU").build();

    // when
    var result = searchService.searchForProducts(search);

    // then
    assertThat(result).hasSize(2);
    assertThat(result)
        .extracting(ProductDto::getPokemonId)
        .containsExactlyInAnyOrder("basep-1", "basep-4");
    assertThat(result.get(0).getName().toLowerCase()).contains("pikachu");
    assertThat(result.get(1).getName().toLowerCase()).contains("pikachu");
  }

  @Test
  void shouldFindProductWithMultipleSearchParams() {
    // given
    var searchParam1 = SearchParam.builder().param("name").value("charizard").build();
    var searchParam2 = SearchParam.builder().param("types").value("fire").build();

    // when
    var result = searchService.searchForProducts(searchParam1, searchParam2);

    // then
    assertThat(result).hasSize(1);
    assertThat(result.get(0).getPokemonId()).isEqualTo("base1-4");
    assertThat(result.get(0).getName().toLowerCase()).contains("charizard");
  }

  @Test
  void shouldThrowExceptionWhenParamsAreNotValid() {
    // given
    var search = SearchParam.builder().param("invalidParam").value("null").build();

    // when + then
    assertThatThrownBy(() -> searchService.searchForProducts(search))
        .isInstanceOf(InvalidSearchException.class);
  }

  @Test
  void shouldThrowExceptionWhenPageSizeIsTooLarge() {
    // given
    var search = SearchParam.builder().param("name").value("Pikachu").build();
    var pageSize = 9999;

    // when + then
    assertThatThrownBy(() -> searchService.searchForProducts(1, pageSize, search))
        .isInstanceOf(InvalidSearchException.class);
  }
}
