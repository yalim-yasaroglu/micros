package tr.com.aselsan.uhkks.micro.cart.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import tr.com.aselsan.uhkks.kafkabase.MessageCarrier;
import tr.com.aselsan.uhkks.kafkabase.enums.MessageType;
import tr.com.aselsan.uhkks.kafkabase.utils.Converter;
import tr.com.aselsan.uhkks.micro.cart.dto.ProductDTO;
import tr.com.aselsan.uhkks.micro.cart.services.CartService;


@Controller
public class KafkaController {

	@Autowired
	private KafkaTemplate<String, MessageCarrier> template;

	@Autowired
	CartService service;

	@KafkaListener(topics = "cart", groupId = "123")
	public void kafkaHandler(MessageCarrier carrier) {
		switch (carrier.getMessageType()) {
		case CREATE:
			ProductDTO prd = (ProductDTO) Converter.castToClass((Map<String, String>) carrier.getPayload(),
					ProductDTO.class);
			MessageCarrier getByIdRequest=new MessageCarrier();
			getByIdRequest.setMessageType(MessageType.GET_BY_ID_FOR_CART);
			getByIdRequest.setPayload(prd);
			
			template.send("product",getByIdRequest);
			break;
		case RESULT:
			if(carrier.getPayload() instanceof String) {
				MessageCarrier cannotCreateCart=new MessageCarrier();
				cannotCreateCart.setMessageType(MessageType.RESULT);
				cannotCreateCart.setPayload("No product found!");
				template.send("api-gw",cannotCreateCart);
			}
			else {
				ProductDTO confirmedPrd = (ProductDTO) Converter.castToClass((Map<String, String>) carrier.getPayload(),
						ProductDTO.class);
				MessageCarrier createdCart=new MessageCarrier();
				createdCart.setMessageType(MessageType.RESULT);
				createdCart.setPayload(service.createCartAfterConfirm(confirmedPrd.getId()));
				template.send("api-gw",createdCart);
			}

		default:
			break;
		}
	}

}
