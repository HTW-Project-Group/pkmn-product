package de.htwberlin.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import de.htwberlin.adapter.CsvAdapter;
import de.htwberlin.entity.ProductEntity;
import de.htwberlin.entity.factory.ProductFactory;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Disabled("Will be fixed after migration")
class ProductServiceTest {

  private ProductService productService;
  private CsvAdapter csvAdapter;

  @BeforeEach
  void setUp() {
    csvAdapter = mock(CsvAdapter.class);
    productService = new ProductService(csvAdapter);
  }

  @Test
  @SneakyThrows
  void shouldReturnProducts() {
    final List<ProductEntity> productList = List.of(ProductFactory.simpleProduct().build());
    when(csvAdapter.readData(anyString())).thenReturn(Optional.of(productList));
    assertThat(productService.findAllProducts()).isEqualTo(productList);
  }

  @Test
  @SneakyThrows
  void shouldReturnProductById() {
    final UUID targetId = UUID.fromString("00000000-0000-0000-0000-000000000000");
    final List<ProductEntity> productList =
        List.of(
            ProductFactory.simpleProduct().build(),
            ProductFactory.simpleProduct().id(targetId).name("Test").price(199).build());

    when(csvAdapter.readData(anyString())).thenReturn(Optional.of(productList));

    var optionalProduct = productService.findProductById(targetId);
    assertThat(optionalProduct).isPresent();

    final ProductEntity result = optionalProduct.get();
    assertThat(result.getId()).isEqualTo(targetId);
    assertThat(result.getName()).isEqualTo("Test");
    assertThat(result.getPrice()).isEqualTo(199);
  }

  @Nested
  class shouldThrowExceptionWhenReadingDataHasFailed {

    @BeforeEach
    void setUp() {
      when(csvAdapter.readData(anyString())).thenReturn(Optional.empty());
    }

    @Test
    @SneakyThrows
    void findAllProducts() {
      assertThatThrownBy(() -> productService.findAllProducts())
          .isInstanceOf(IOException.class)
          .hasMessage("Failed to load CSV");
    }

    @Test
    @SneakyThrows
    void findProductsById() {
      assertThatThrownBy(() -> productService.findProductById(any()))
          .isInstanceOf(IOException.class)
          .hasMessage("Failed to load CSV");
    }
  }
}
