package com.example.pong;

import com.example.pong.rabbit.MessageDto;
import com.example.pong.rabbit.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pong")
@RequiredArgsConstructor
public class PongController {

  private final Producer producer;

  @PostMapping
  public ResponseEntity<Void> sendMessage(@RequestBody MessageDto message) {
    producer.sendMessage(message);
    return ResponseEntity.ok().build();
  }

}
