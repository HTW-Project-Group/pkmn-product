package de.htwberlin.port.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

  @Value("${queue.name}")
  private String queueName;

  @Value("${queue.exchange}")
  private String queueExchange;

  @Value("${queue.routing_key}")
  private String queueRoutingKey;

  @Bean
  public Queue queue() {
    return new Queue(queueName);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(queueExchange);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(queue()).to(exchange()).with(queueRoutingKey);
  }
}
