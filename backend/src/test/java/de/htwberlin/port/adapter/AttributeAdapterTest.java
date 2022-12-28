package de.htwberlin.port.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.appservice.dto.ProductFactory;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttributeAdapterTest {

  private AttributeAdapter<ProductDto> attributeAdapter;

  @BeforeEach
  void setUp() {
    attributeAdapter = new AttributeAdapter<>();
  }

  @Test
  void shouldCopyAttributes() {
    // given
    var product = ProductFactory.simpleProduct().name("Charizard");
    var source = product.build();
    var target = product.name("Charizard GX").build();

    // when
    var result = attributeAdapter.copyAttributes(source, target);

    // then
    assertThat(result.getName()).isEqualTo("Charizard GX");
    assertThat(result.getId()).isEqualTo(source.getId());
  }

  @Test
  void shouldCopyOnlyNotNullAttributes() {
    // given
    var source =
        ProductFactory.simpleProduct()
            .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            .condition(5)
            .price(55.5)
            .build();
    var target = ProductDto.builder().id(null).condition(0).price(0.0).name("Charizard GX").build();

    // when
    var result = attributeAdapter.copyAttributes(source, target);

    // then
    assertThat(result.getName()).isEqualTo("Charizard GX");
    assertThat(result.getId()).isEqualTo(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    assertThat(result.getCondition()).isEqualTo(5);
    assertThat(result.getPrice()).isEqualTo(55.5);
  }

  @Test
  void shouldReturnTargetObjectIfSourceIsNull() {
    // given
    var target = ProductDto.builder().name("Charizard GX").build();

    // when
    var result = attributeAdapter.copyAttributes(null, target);

    // then
    assertThat(result.getName()).isEqualTo("Charizard GX");
    assertThat(result).isEqualTo(target);
  }

  @Test
  void shouldReturnSourceIfTargetIsNull() {
    // given
    var source = ProductFactory.simpleProduct().build();

    // when
    var result = attributeAdapter.copyAttributes(source, null);

    // then
    assertThat(result).isEqualTo(source);
  }
}
