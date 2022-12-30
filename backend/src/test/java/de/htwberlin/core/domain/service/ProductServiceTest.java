package de.htwberlin.core.domain.service;

import static org.assertj.core.api.Assertions.*;

import de.htwberlin.core.appservice.mapper.IProductMapper;
import de.htwberlin.core.appservice.dto.IProductMapperImpl;
import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.appservice.dto.ProductFactory;
import de.htwberlin.core.domain.model.Product;
import de.htwberlin.core.domain.repository.IProductRepository;
import de.htwberlin.core.domain.repository.ProductInMemoryRepository;
import de.htwberlin.core.domain.service.impl.ProductService;
import de.htwberlin.core.appservice.mapper.AttributeMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

  private IProductService productService;
  private IProductRepository productRepository;
  private IProductMapper productMapper;

  @BeforeEach
  void setUp() {
    productRepository = new ProductInMemoryRepository();
    productMapper = new IProductMapperImpl();
    var attributeMapper = new AttributeMapper<ProductDto>();

    productService = new ProductService(productRepository, productMapper, attributeMapper);
  }

  @Test
  void shouldReturnAllProducts() {
    // given
    final UUID id1 = UUID.fromString("10000000-0000-0000-0000-000000000000");
    final UUID id2 = UUID.fromString("20000000-0000-0000-0000-000000000000");
    final List<Product> products =
        productMapper.toEntities(
            List.of(
                ProductFactory.simpleProduct().id(id1).build(),
                ProductFactory.simpleProduct().id(id2).build()));
    productRepository.saveAll(products);

    // when
    var result = productService.findAllProducts();

    // then
    assertThat(result).hasSize(2);
    assertThat(result).extracting(ProductDto::getId).containsExactlyInAnyOrder(id1, id2);
  }

  @Test
  void shouldReturnProductById() {
    // given
    final UUID targetId = UUID.fromString("00000000-0000-0000-0000-000000000000");
    final ProductDto targetDto =
        ProductFactory.simpleProduct().id(targetId).name("Test").price(199).build();
    final Product targetEntity = productMapper.toEntity(targetDto);
    productRepository.save(targetEntity);

    // when
    final var result = productService.findProductById(targetId);

    // then
    assertThat(result).isPresent();
    var product = result.get();
    assertThat(product.getId()).isEqualTo(targetId);
    assertThat(product.getName()).isEqualTo("Test");
    assertThat(product.getPrice()).isEqualTo(199);
  }

  @Test
  void shouldReturnProductByPokemondId() {
    // given
    final String targetPokemonId = "test-pkmn";
    final ProductDto targetDto =
        ProductFactory.simpleProduct().pokemonId(targetPokemonId).name("Test").price(199).build();
    final Product targetEntity = productMapper.toEntity(targetDto);
    productRepository.save(targetEntity);

    // when
    final var result = productService.findProductByPokemonId(targetPokemonId);

    // then
    assertThat(result).isPresent();
    var product = result.get();
    assertThat(product.getPokemonId()).isEqualTo(targetPokemonId);
    assertThat(product.getName()).isEqualTo("Test");
    assertThat(product.getPrice()).isEqualTo(199);
  }
}
