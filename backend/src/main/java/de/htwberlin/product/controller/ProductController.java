package de.htwberlin.product.controller;

import de.htwberlin.product.dto.ProductDto;
import de.htwberlin.product.exception.ProductNotFoundException;
import de.htwberlin.product.service.ProductService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductDto>> getProducts() {
    return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id)
      throws ProductNotFoundException {
    return new ResponseEntity<>(
        productService.findProductById(id).orElseThrow(ProductNotFoundException::new),
        HttpStatus.OK);
  }

  @GetMapping("/pkmn/{pokemonId}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable("pokemonId") String pokemonId)
      throws ProductNotFoundException {
    return new ResponseEntity<>(
        productService.findProductByPokemonId(pokemonId).orElseThrow(ProductNotFoundException::new),
        HttpStatus.OK);
  }
}
