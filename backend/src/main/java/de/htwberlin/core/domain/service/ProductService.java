package de.htwberlin.core.domain.service;

import de.htwberlin.core.appservice.dto.IProductMapper;
import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.domain.repository.IProductRepository;
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
public class ProductService implements IProductService {

  private final IProductRepository productRepository;
  private final IProductMapper productMapper;

  @Override
  public List<ProductDto> findAllProducts() {
    return productRepository.findAll().stream().map(productMapper::toDto).toList();
  }

  @Override
  public Optional<ProductDto> findProductById(UUID id) {
    final var entity = productRepository.findProductById(id);
    return entity.map(productMapper::toDto);
  }

  @Override
  public Optional<ProductDto> findProductByPokemonId(String id) {
    final var entity = productRepository.findProductByPokemonId(id);
    return entity.map(productMapper::toDto);
  }

  @Override
  public ProductDto createProduct(ProductDto dto) {
    productRepository.save(productMapper.toEntity(dto));
    return dto;
  }

  @Override
  public ProductDto updateProduct(ProductDto dto) {
    // Update implementation if needed
    return createProduct(dto);
  }
}
