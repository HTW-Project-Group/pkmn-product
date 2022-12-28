package de.htwberlin.port.product;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.htwberlin.core.appservice.dto.IProductMapperImpl;
import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.appservice.dto.ProductFactory;
import de.htwberlin.core.domain.repository.IProductRepository;
import de.htwberlin.core.domain.repository.ProductInMemoryRepository;
import de.htwberlin.core.domain.service.IProductService;
import de.htwberlin.core.domain.service.impl.ProductService;
import de.htwberlin.port.adapter.AttributeAdapter;
import de.htwberlin.port.annotation.IntegrationTest;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@IntegrationTest
class ProductControllerIntegrationTest {

  private MockMvc mvc;

  @MockBean private IProductService productService;
  @MockBean private IProductRepository repository;
  @MockBean private ProductController controller;

  @BeforeEach
  void setUp() {
    // given
    var productMapper = new IProductMapperImpl();
    var attributeAdapter = new AttributeAdapter<ProductDto>();
    repository = new ProductInMemoryRepository();
    productService = new ProductService(repository, productMapper, attributeAdapter);
    controller = new ProductController(productService);

    productService.createProduct(
        ProductFactory.simpleProduct()
            .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            .pokemonId("basep-4")
            .build());
    productService.createProduct(ProductFactory.simpleProduct().build());

    mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void shouldReturnAllProductsThenStatus200() throws Exception {
    // given + when
    mvc.perform(get("/v1/products"))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void shouldReturnProductByIdThenStatus200() throws Exception {
    // given + when
    mvc.perform(get("/v1/products/00000000-0000-0000-0000-000000000000"))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000000"));
  }

  @Test
  void shouldReturnProductByPokemonIdThenStatus200() throws Exception {
    // given + when
    mvc.perform(get("/v1/products/pkmn/basep-4"))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000000"))
        .andExpect(jsonPath("$.pokemonId").value("basep-4"));
  }

  @Test
  void shouldCreateProductThenStatus201() throws Exception {
    // given
    var productJson =
        "{"
            + "\"id\": \"10000000-0000-0000-0000-000000000000\","
            + "\"name\": \"Pikachu\","
            + "\"price\": 100.0,"
            + "\"description\": null,"
            + "\"condition\": 10,"
            + "\"pokemonId\": \"basep-1\""
            + "}";

    // when
    mvc.perform(
            post("/v1/products/new").contentType(MediaType.APPLICATION_JSON).content(productJson))

        // then
        .andExpect(status().isCreated())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("10000000-0000-0000-0000-000000000000"))
        .andExpect(jsonPath("$.name").value("Pikachu"))
        .andExpect(jsonPath("$.price").value(100.0))
        .andExpect(jsonPath("$.pokemonId").value("basep-1"));
  }

  @Test
  void shouldUpdateProductThenStatus200() throws Exception {
    // given
    var productJson = "{\"name\": \"Super Pikachu\"}";

    // when
    mvc.perform(
            put("/v1/products/00000000-0000-0000-0000-000000000000/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000000"))
        .andExpect(jsonPath("$.name").value("Super Pikachu"));
  }
}
