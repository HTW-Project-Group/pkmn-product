package de.htwberlin.port.product.controller;

import de.htwberlin.port.dto.ProductExportDto;
import de.htwberlin.port.product.producer.ProductProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/v1/queue")
public class QueueController {

  private final ProductProducer productProducer;

  @PostMapping("/basket/add")
  public void addProductToBasketQueue(@RequestBody ProductExportDto productDto) {
    productProducer.sendProduct(productDto);
  }
}