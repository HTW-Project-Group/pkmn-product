package de.htwberlin.port.product.controller;

import de.htwberlin.core.appservice.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/v1/queue")
public class QueueController {

  @PostMapping("/basket/add")
  public ResponseEntity<ProductDto> addProductToBasketQueue() {
    return null;
  }
}
