package de.htwberlin.port.product.controller;

import de.htwberlin.core.appservice.mapper.IProductMapper;
import de.htwberlin.core.domain.model.Product;
import de.htwberlin.core.domain.service.IProductService;
import de.htwberlin.port.dto.ProductDto;
import de.htwberlin.port.exception.ProductNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product")
public class ProductController {

  // Approved by Tobi

  private final IProductService productService;
  private final IProductMapper productMapper;

  @GetMapping
  public ResponseEntity<List<Product>> getProducts(@RequestParam Optional<Integer> amount) {
    return amount
        .map(
            integer ->
                new ResponseEntity<>(
                    productService.findCertainAmountOfProducts(integer), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id)
      throws ProductNotFoundException {
    return new ResponseEntity<>(
        productService.findProductById(id).orElseThrow(ProductNotFoundException::new),
        HttpStatus.OK);
  }

  @GetMapping("/pkmn/{pokemonId}")
  public ResponseEntity<Product> getProductById(@PathVariable("pokemonId") String pokemonId)
      throws ProductNotFoundException {
    return new ResponseEntity<>(
        productService.findProductByPokemonId(pokemonId).orElseThrow(ProductNotFoundException::new),
        HttpStatus.OK);
  }

  @PostMapping("/new")
  public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
    final Product product = productMapper.toEntity(productDto);
    return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
  }

  @PutMapping("/{id}/edit")
  public ResponseEntity<Product> updateProduct(
      @RequestBody ProductDto productDto, @PathVariable("id") UUID id) {
    final Product product = productMapper.toEntity(productDto);
    return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
  }
}
