package de.htwberlin.product.controller;

import de.htwberlin.product.entity.ProductEntity;
import de.htwberlin.product.exception.ProductNotFoundException;
import de.htwberlin.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @GetMapping
  @CrossOrigin
  public ResponseEntity<List<ProductEntity>> getProducts() {
    return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @CrossOrigin
  public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") UUID id)
      throws ProductNotFoundException {
    return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
  }
}
