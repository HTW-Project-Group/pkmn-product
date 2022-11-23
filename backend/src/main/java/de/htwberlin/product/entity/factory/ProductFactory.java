package de.htwberlin.product.entity.factory;

import com.github.javafaker.Faker;
import de.htwberlin.product.entity.ProductEntity;
import java.util.Locale;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ProductFactory {

  private static final Faker faker = new Faker(Locale.GERMAN);

  public static ProductEntity.ProductEntityBuilder simpleProduct() {
    return ProductEntity.builder()
        .id(UUID.randomUUID())
        .name(faker.pokemon().name())
        .price(faker.number().randomDouble(2, 100, 1000));
  }
}
