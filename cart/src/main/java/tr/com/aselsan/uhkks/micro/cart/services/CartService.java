package tr.com.aselsan.uhkks.micro.cart.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.com.aselsan.uhkks.micro.cart.controllers.RestClient;
import tr.com.aselsan.uhkks.micro.cart.dto.ProductDTO;
import tr.com.aselsan.uhkks.micro.cart.entities.Cart;
import tr.com.aselsan.uhkks.micro.cart.repositories.CartRepository;

@Service
public class CartService {

  @Autowired
  CartRepository repository;

  @Autowired
  RestClient restClient;

  @Value("${productService.url}")
  String productUrl;

  public List<Cart> getAllCarts(){
    return repository.findAll();
  }

  public Cart getCartById(UUID id){
    return repository.getOne(id);
  }

  public boolean deleteCartById(UUID id){
    repository.deleteById(id);
    return true;
  }

  public Cart createCart(UUID productId) throws Exception{
    Cart cart= new Cart();
    // 1. gidip sor (message)
    // 2. zaten bil (event)


    ProductDTO dto= (ProductDTO) restClient.get(productUrl+'/'+productId.toString(), ProductDTO.class);


    cart.getProductIds().add(productId);
    cart.setId(UUID.randomUUID());
    return repository.save(cart);
  }


  public boolean buy(UUID cartId) {
    Cart cart = repository.findById(cartId).get();
    List<UUID> productIds=cart.getProductIds();
    try {
      for(UUID productId : productIds){
        ProductDTO dto= (ProductDTO) restClient.get(productUrl+'/'+productId.toString(), ProductDTO.class);
      }
    } catch (Exception e){
      System.out.println("At least one product is not listed anymore");
      return false;
    }

    repository.deleteById(cartId);
    try {
      for (UUID productId : productIds) {
        restClient.delete(productUrl + '/' + productId.toString());
      }
    }catch (Exception e){
      System.out.println("At least one product is not listed anymore");
      return false;
    }
    return true;

  }
}
