package tr.com.aselsan.uhkks.micro.cart.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class RestClient {

  public Object get(String URL, Class responseClass) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity response= restTemplate.getForEntity(URL, responseClass);
    if(!response.getStatusCode().is2xxSuccessful()){
      throw new Exception("Exception on request! got "+ response.getStatusCode().name()+ " as response!");
    }
    return response.getBody();
  }

  public Object post(String URL,Object requestObject, Class responseClass) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity response= restTemplate.postForEntity(URL, requestObject, responseClass);
    if(!response.getStatusCode().is2xxSuccessful()){
      throw new Exception("Exception on request! got "+ response.getStatusCode().name()+ " as response!");
    }
    return response.getBody();
  }

  public boolean delete(String URL) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(URL);
    return true;
  }

}
