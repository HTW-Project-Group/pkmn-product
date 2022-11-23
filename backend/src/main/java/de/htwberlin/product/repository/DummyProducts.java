package de.htwberlin.product.repository;

import de.htwberlin.product.entity.ProductEntity;
import de.htwberlin.product.entity.factory.ProductFactory;
import java.util.List;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DummyProducts {
  public static final List<ProductEntity> PRODUCT_LIST =
      IntStream.range(0, 10).mapToObj(v -> ProductFactory.simpleProduct().build()).toList();
}
