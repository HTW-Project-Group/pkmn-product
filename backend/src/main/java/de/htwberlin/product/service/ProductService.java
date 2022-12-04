package de.htwberlin.product.service;

import de.htwberlin.product.dto.ProductDto;
import de.htwberlin.product.dto.ProductMapper;
import de.htwberlin.product.exception.ProductNotFoundException;
import de.htwberlin.product.model.ProductEntity;
import de.htwberlin.product.model.repository.ProductRepository;
import java.util.List;
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

  public ProductDto findProductById(UUID id) throws ProductNotFoundException {
    final ProductEntity entity =
        productRepository.findProductById(id).orElseThrow(ProductNotFoundException::new);
    return productMapper.toDto(entity);
  }
}
