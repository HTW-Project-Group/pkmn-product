package de.htwberlin.port.product.controller;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.domain.service.IProductService;
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
@RequestMapping("/v1/products")
@CrossOrigin
public class ProductController {

  private final IProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductDto>> getProducts(@RequestParam Optional<Integer> amount) {
    return amount
        .map(
            integer ->
                new ResponseEntity<>(
                    productService.findCertainAmountOfProducts(integer), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK));
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

  @PostMapping("/new")
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}/edit")
  public ResponseEntity<ProductDto> updateProduct(
      @RequestBody ProductDto productDto, @PathVariable("id") UUID id) {
    return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.OK);
  }
}
