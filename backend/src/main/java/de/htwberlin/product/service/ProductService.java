package de.htwberlin.product.service;

import de.htwberlin.product.entity.ProductEntity;
import de.htwberlin.product.exception.ProductNotFoundException;
import de.htwberlin.product.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;

  public List<ProductEntity> findAllProducts() {
    return productRepository.findAllProducts();
  }

  public ProductEntity findProductById(UUID id) throws ProductNotFoundException {
    return productRepository.findProductById(id).orElseThrow(ProductNotFoundException::new);
  }
}
