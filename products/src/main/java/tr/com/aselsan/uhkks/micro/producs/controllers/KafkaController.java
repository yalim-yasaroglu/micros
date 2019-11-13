package tr.com.aselsan.uhkks.micro.producs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import tr.com.aselsan.uhkks.kafkabase.MessageCarrier;
import tr.com.aselsan.uhkks.kafkabase.enums.MessageType;

@Controller
public class KafkaController {

  @Autowired
  private KafkaTemplate<String, MessageCarrier> template;

  @KafkaListener(topics = {"product"}, groupId = "123")
  public void listenToKafka(MessageCarrier carrier){
    carrier.setMessageType(MessageType.RESULT);
    template.send("api-gw",carrier);
  }

}
