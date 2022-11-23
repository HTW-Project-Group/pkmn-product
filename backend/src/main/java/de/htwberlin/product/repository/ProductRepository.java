package de.htwberlin.product.repository;

import static de.htwberlin.product.repository.DummyProducts.PRODUCT_LIST;
import static java.util.Optional.ofNullable;

import de.htwberlin.product.entity.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

// later: replace with real Repository connected to DB
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

  default List<ProductEntity> findAllProducts() {
    return PRODUCT_LIST;
  }

  default Optional<ProductEntity> findProductById(UUID id) {
    return ofNullable(
        PRODUCT_LIST.stream()
            .collect(Collectors.toMap(ProductEntity::getId, entity -> entity))
            .get(id));
  }
}
