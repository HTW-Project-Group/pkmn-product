package de.htwberlin.product.model.repository;

import de.htwberlin.product.model.ProductEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

  Optional<ProductEntity> findProductById(UUID id);

  Optional<ProductEntity> findProductByPokemonId(String pokemonId);
}
