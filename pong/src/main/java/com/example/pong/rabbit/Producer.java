package com.example.pong.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(MessageDto message) {
    log.info("Sending message -> {}", message);
    rabbitTemplate.convertAndSend(MessagingConfiguration.topicExchangeName, MessagingConfiguration.pingRoutingKey, message);
  }

}
