package com.example.ping;

import com.example.ping.rabbit.MessageDto;
import com.example.ping.rabbit.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor
public class PingController {

  private final Producer producer;

  @PostMapping
  public ResponseEntity<Void> sendMessage(@RequestBody MessageDto message) {
    producer.sendMessage(message);
    return ResponseEntity.ok().build();
  }

}
