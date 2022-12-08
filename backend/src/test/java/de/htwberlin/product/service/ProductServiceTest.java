package de.htwberlin.product.service;

import static org.assertj.core.api.Assertions.*;

import de.htwberlin.product.dto.IProductMapper;
import de.htwberlin.product.dto.IProductMapperImpl;
import de.htwberlin.product.dto.ProductDto;
import de.htwberlin.product.dto.ProductFactory;
import de.htwberlin.product.model.ProductEntity;
import de.htwberlin.product.model.repository.IProductRepository;
import de.htwberlin.product.model.repository.ProductInMemoryRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

  private ProductService productService;
  private IProductRepository productRepository;
  private IProductMapper productMapper;

  @BeforeEach
  void setUp() {
    productRepository = new ProductInMemoryRepository();
    productMapper = new IProductMapperImpl();
    productService = new ProductService(productRepository, productMapper);
  }

  @Test
  void shouldReturnProductById() {
    // given
    final UUID targetId = UUID.fromString("00000000-0000-0000-0000-000000000000");
    final ProductDto targetDto =
        ProductFactory.simpleProduct().id(targetId).name("Test").price(199).build();
    final ProductEntity targetEntity = productMapper.toEntity(targetDto);
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
    final ProductEntity targetEntity = productMapper.toEntity(targetDto);
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
