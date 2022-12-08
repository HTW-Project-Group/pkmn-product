package de.htwberlin.product.service;

import de.htwberlin.product.dto.ProductDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

  List<ProductDto> findAllProducts();

  Optional<ProductDto> findProductById(UUID id);

  Optional<ProductDto> findProductByPokemonId(String id);

  ProductDto createProduct(ProductDto dto);

  ProductDto updateProduct(ProductDto dto);
}
