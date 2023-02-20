package de.htwberlin.core.domain.service.impl;

import de.htwberlin.core.appservice.mapper.AttributeMapper;
import de.htwberlin.core.domain.model.Product;
import de.htwberlin.core.domain.repository.IProductRepository;
import de.htwberlin.core.domain.service.IProductService;
import de.htwberlin.port.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class ProductService implements IProductService {

  private final IProductRepository productRepository;
  private final AttributeMapper<Product> attributeMapper;

  @Override
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public List<Product> findCertainAmountOfProducts(int amount) {
    List<Product> result = new ArrayList<>();
    long amountInRepository = productRepository.count();
    for (int i = 0; i < amount; i++) {
      int index = (int) (Math.random() * amountInRepository);
      var entity = productRepository.findAll(PageRequest.of(index, 1));
      result.add(entity.getContent().get(0));
    }
    return result;
  }

  @Override
  public Optional<Product> findProductById(UUID id) {
    return productRepository.findProductById(id);
  }

  @Override
  public Optional<Product> findProductByPokemonId(String id) {
    return productRepository.findProductByPokemonId(id);
  }

  @Override
  public Product createProduct(Product product) {
    productRepository.save(product);
    return product;
  }

  @Override
  public Product updateProduct(Product product, UUID id) {
    var oldProduct = findProductById(id).orElseThrow(ProductNotFoundException::new);
    var newProduct = attributeMapper.copyAttributes(oldProduct, product);
    productRepository.save(newProduct);
    return newProduct;
  }
}
