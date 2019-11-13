package tr.com.aselsan.uhkks.micro.cart.controllers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaController {

  @KafkaListener(topics = "cart", groupId = "123")
  public void kafkaHandler(ConsumerRecord<?, ?> cr){
    System.out.println(cr);
  }

}
