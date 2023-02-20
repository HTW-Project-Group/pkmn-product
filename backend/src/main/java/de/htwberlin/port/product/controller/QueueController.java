package de.htwberlin.port.product.controller;

import de.htwberlin.port.dto.BasketItemDto;
import de.htwberlin.port.product.producer.ProductProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product/queue")
public class QueueController {

  private final ProductProducer productProducer;

  @PostMapping("/basket/add")
  public void addProductToBasketQueue(@RequestBody BasketItemDto basketItemDto) {
    productProducer.sendProduct(basketItemDto);
  }
}
