package de.htwberlin.product.dto;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ProductFactory {

  private static final Faker faker = new Faker(Locale.GERMAN);

  public static ProductDto.ProductDtoBuilder simpleProduct() {
    return ProductDto.builder()
        .id(UUID.randomUUID())
        .name(faker.pokemon().name())
        .price(faker.number().randomDouble(2, 100, 1000));
  }
}
