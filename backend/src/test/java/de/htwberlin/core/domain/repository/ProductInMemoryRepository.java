package de.htwberlin.core.domain.repository;

import de.htwberlin.core.domain.model.Product;
import java.util.*;

public class ProductInMemoryRepository extends BaseInMemoryRepository<Product, UUID>
    implements IProductRepository {

  private final Map<UUID, Product> entities = new HashMap<>();

  @Override
  public Optional<Product> findProductById(UUID id) {
    return Optional.ofNullable(entities.get(id));
  }

  @Override
  public Optional<Product> findProductByPokemonId(String pokemonId) {
    return entities.values().stream().filter(e -> e.getPokemonId().equals(pokemonId)).findFirst();
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public <S extends Product> S save(S entity) {
    entities.put(entity.getId(), entity);
    return entity;
  }
}
