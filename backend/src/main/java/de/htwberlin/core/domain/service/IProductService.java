package de.htwberlin.core.domain.service;

import de.htwberlin.core.appservice.dto.ProductDto;
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
