package tr.com.aselsan.uhkks.micro.apigw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import tr.com.aselsan.uhkks.kafkabase.MessageCarrier;

@Controller
public class KafkaContoller {

  @Autowired
  private KafkaTemplate<String, MessageCarrier> template;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @KafkaListener(topics = {"api-gw"}, groupId = "1")
  public void listen(MessageCarrier carrier) throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    Message<MessageCarrier> mc =new GenericMessage(mapper.writeValueAsBytes(carrier));
    simpMessagingTemplate.send("/topic/fromServer",mc);

  }

  @MessageMapping("/product")
  public String sendToProduct(MessageCarrier carrier){
    template.send("product", carrier);
    return "sent!";
  }

  @MessageMapping("/cart")
  public String sendToCart(MessageCarrier carrier){
    template.send("product", carrier);
    return "sent!";
  }

}
