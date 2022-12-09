package de.htwberlin.core.domain.repository;

import de.htwberlin.core.domain.model.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {

  Optional<Product> findProductById(UUID id);

  Optional<Product> findProductByPokemonId(String pokemonId);
}
