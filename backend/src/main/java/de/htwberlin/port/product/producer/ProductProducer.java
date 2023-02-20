package de.htwberlin.port.product.producer;

import de.htwberlin.port.dto.BasketItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductProducer {

  @Value("${queue.exchange}")
  private String exchange;

  @Value("${queue.routing_key}")
  private String routingKey;

  private final RabbitTemplate rabbitTemplate;

  public void sendProduct(BasketItemDto product) {
    log.info(String.format("Product sent -> %s", product.getPokemonId()));
    rabbitTemplate.convertAndSend(exchange, routingKey, product);
  }
}
