package de.htwberlin.product.service;

import de.htwberlin.product.dto.ProductDto;
import de.htwberlin.product.dto.ProductMapper;
import de.htwberlin.product.model.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDto> findAllProducts() {
    return productRepository.findAll().stream().map(productMapper::toDto).toList();
  }

  public Optional<ProductDto> findProductById(UUID id) {
    final var entity = productRepository.findProductById(id);
    return entity.map(productMapper::toDto);
  }

  public Optional<ProductDto> findProductByPokemonId(String id) {
    final var entity = productRepository.findProductByPokemonId(id);
    return entity.map(productMapper::toDto);
  }

  public void createProduct(ProductDto dto) {
    productRepository.save(productMapper.toEntity(dto));
  }
}
