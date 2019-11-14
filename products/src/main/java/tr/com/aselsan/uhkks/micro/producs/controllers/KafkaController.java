package tr.com.aselsan.uhkks.micro.producs.controllers;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import tr.com.aselsan.uhkks.kafkabase.MessageCarrier;
import tr.com.aselsan.uhkks.kafkabase.enums.MessageType;
import tr.com.aselsan.uhkks.kafkabase.utils.Converter;
import tr.com.aselsan.uhkks.micro.producs.entities.Product;
import tr.com.aselsan.uhkks.micro.producs.services.ProductService;

@Controller
public class KafkaController {

	@Autowired
	private KafkaTemplate<String, MessageCarrier> template;

	@Autowired
	private ProductService service;

	@KafkaListener(topics = { "product" }, groupId = "123")
	public void listenToKafka(MessageCarrier carrier) {
		switch (carrier.getMessageType()) {
		case GET_BY_ID:
			Product prd = (Product) Converter.castToClass((Map<String, String>) carrier.getPayload(), Product.class);
			if(service.getProductById(prd.getId()).isPresent()) {
				MessageCarrier response = new MessageCarrier();
				response.setMessageType(MessageType.RESULT);
				response.setPayload(service.getProductById(prd.getId()).get());
				template.send("api-gw", response);
			}
			else {
				MessageCarrier response = new MessageCarrier();
				response.setMessageType(MessageType.RESULT);
				String errorMessage="Product not found";
				response.setPayload(errorMessage);
				template.send("api-gw", response);
			}
			
			break;

		case GET_BY_ID_FOR_CART:
			Product prd2 = (Product) Converter.castToClass((Map<String, String>) carrier.getPayload(), Product.class);
			if(service.getProductById(prd2.getId()).isPresent()) {
				MessageCarrier response = new MessageCarrier();
				response.setMessageType(MessageType.RESULT);
				response.setPayload(service.getProductById(prd2.getId()).get());
				template.send("cart", response);
			}
			else {
				MessageCarrier response = new MessageCarrier();
				response.setMessageType(MessageType.RESULT);
				String errorMessage="Product not found";
				response.setPayload(errorMessage);
				template.send("cart", response);
			}
			break;
			
		case GET_ALL:
			
			MessageCarrier response = new MessageCarrier();
			response.setMessageType(MessageType.RESULT);
			response.setPayload(service.getAllProducts());
			template.send("api-gw", response);
			
			break;

		
		default:
			break;
		}

	}

}
