package com.example.ping.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

  static final String topicExchangeName = "ping-pong-exchange";
  static final String pongQueue = "pong-queue";
  static final String pongRoutingKey = "pong.key";
  static final String pingQueue = "ping-queue";
  static final String pingRoutingKey = "ping.key";


  @Bean
  Queue pongQueue() {
    return new Queue(pongQueue);
  }

  @Bean
  Queue pingQueue() {
    return new Queue(pingQueue);
  }


  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding pingBinding(TopicExchange exchange) {
    return BindingBuilder
        .bind(pingQueue())
        .to(exchange)
        .with(pingRoutingKey);
  }


  @Bean
  Binding pongBinding(TopicExchange exchange) {
    return BindingBuilder
        .bind(pongQueue())
        .to(exchange)
        .with(pongRoutingKey);
  }

  @Bean
  Jackson2JsonMessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }

//  @Bean
//  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                           MessageListenerAdapter listenerAdapter) {
//    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//    container.setConnectionFactory(connectionFactory);
//    container.setQueueNames(pingQueue);
//    container.setMessageListener(listenerAdapter);
//    return container;
//  }
//
//  @Bean
//  MessageListenerAdapter listenerAdapter(Consumer consumer) {
//    return new MessageListenerAdapter(consumer, "consumeMessage");
//  }

}
