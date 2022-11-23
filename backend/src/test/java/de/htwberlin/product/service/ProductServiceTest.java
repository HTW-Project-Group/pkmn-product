package de.htwberlin.product.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.htwberlin.product.entity.ProductEntity;
import de.htwberlin.product.entity.factory.ProductFactory;
import de.htwberlin.product.exception.ProductNotFoundException;
import de.htwberlin.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

  private ProductService productService;
  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    productService = new ProductService(productRepository);
  }

  @Test
  @SneakyThrows
  void shouldReturnProducts() {
    final List<ProductEntity> productList = List.of(ProductFactory.simpleProduct().build());
    when(productRepository.findAllProducts()).thenReturn(productList);
    assertThat(productService.findAllProducts()).isEqualTo(productList);
  }

  @Test
  @SneakyThrows
  void shouldReturnProductById() {
    final UUID targetId = UUID.fromString("00000000-0000-0000-0000-000000000000");
    final ProductEntity target =
        ProductFactory.simpleProduct().id(targetId).name("Test").price(199).build();

    when(productRepository.findProductById(targetId)).thenReturn(Optional.of(target));

    final ProductEntity result = productService.findProductById(targetId);
    assertThat(result.getId()).isEqualTo(targetId);
    assertThat(result.getName()).isEqualTo("Test");
    assertThat(result.getPrice()).isEqualTo(199);
  }

  @Test
  @SneakyThrows
  void shouldThrowExceptionWhenNoProductWasFoundById() {
    when(productRepository.findProductById(any())).thenReturn(Optional.empty());
    assertThatThrownBy(() -> productService.findProductById(any()))
        .isInstanceOf(ProductNotFoundException.class);
  }
}
