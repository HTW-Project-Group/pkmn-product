package de.htwberlin.controller;

import de.htwberlin.entity.ProductEntity;
import de.htwberlin.service.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductEntity>> getProduct() throws IOException {
    return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductEntity> getProductEntityByID(@PathVariable("id") UUID id)
      throws IOException {
    Optional<ProductEntity> entity = productService.findProductById(id);
    return entity
        .map(productEntity -> new ResponseEntity<>(productEntity, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
