package de.htwberlin.core.domain.service;

import de.htwberlin.core.domain.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

  List<Product> findAllProducts();

  List<Product> findCertainAmountOfProducts(int amount);

  Optional<Product> findProductById(UUID id);

  Optional<Product> findProductByPokemonId(String id);

  Product createProduct(Product product);

  Product updateProduct(Product product, UUID id);
}
