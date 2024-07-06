package com.example.pong.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

  @RabbitListener(queues = MessagingConfiguration.pongQueue)
  public void consumeMessage(MessageDto message) {
    log.info("Received message - {}", message.getMessage());
  }

}
