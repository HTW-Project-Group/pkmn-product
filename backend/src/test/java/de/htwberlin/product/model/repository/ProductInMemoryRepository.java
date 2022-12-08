package de.htwberlin.product.model.repository;

import de.htwberlin.product.model.ProductEntity;
import java.util.*;

public class ProductInMemoryRepository extends BaseInMemoryRepository<ProductEntity, UUID>
    implements IProductRepository {

  private final Map<UUID, ProductEntity> entities = new HashMap<>();

  @Override
  public Optional<ProductEntity> findProductById(UUID id) {
    return Optional.ofNullable(entities.get(id));
  }

  @Override
  public Optional<ProductEntity> findProductByPokemonId(String pokemonId) {
    return entities.values().stream().filter(e -> e.getPokemonId().equals(pokemonId)).findFirst();
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public <S extends ProductEntity> S save(S entity) {
    entities.put(entity.getId(), entity);
    return entity;
  }
}
