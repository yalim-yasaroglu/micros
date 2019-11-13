package tr.com.aselsan.uhkks.micro.apigw.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ApiGwConfig implements WebSocketMessageBrokerConfigurer {


  public void configureMessageBroker(MessageBrokerRegistry config) {
    //carries messages back to client on destinationPrefixes
    config.enableSimpleBroker("/topic");
    //config for server side process prefix
    config.setApplicationDestinationPrefixes("/app");
  }


  public void registerStompEndpoints(StompEndpointRegistry registry) {
    //socks.js connection url
    registry.addEndpoint("/super-shopping-cart").withSockJS();
  }
  @Bean
  public NewTopic apiGwTopic() {
    return new NewTopic("api-gw", 1, (short) 1);
  }
  @Bean
  public NewTopic productTopic() {
    return new NewTopic("product", 1, (short) 1);
  }
  @Bean
  public NewTopic cartTopic() {
    return new NewTopic("cart", 1, (short) 1);
  }
}
