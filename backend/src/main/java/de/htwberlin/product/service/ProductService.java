package de.htwberlin.product.service;

import de.htwberlin.product.exception.ProductNotFoundException;
import de.htwberlin.product.model.ProductEntity;
import de.htwberlin.product.model.dto.ProductDto;
import de.htwberlin.product.model.dto.ProductMapper;
import de.htwberlin.product.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;
  private ProductMapper productMapper;

  public List<ProductDto> findAllProducts() {
    return productRepository.findAll().stream().map(entity -> productMapper.toDto(entity)).toList();
  }

  public ProductDto findProductById(UUID id) throws ProductNotFoundException {
    final ProductEntity entity =
        productRepository.findProductById(id).orElseThrow(ProductNotFoundException::new);
    return productMapper.toDto(entity);
  }
}
