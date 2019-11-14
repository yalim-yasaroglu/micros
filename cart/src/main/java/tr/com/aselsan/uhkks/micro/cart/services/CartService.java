package tr.com.aselsan.uhkks.micro.cart.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.aselsan.uhkks.micro.cart.entities.Cart;
import tr.com.aselsan.uhkks.micro.cart.repositories.CartRepository;

@Service
public class CartService {

  @Autowired
  CartRepository repository;

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

  public Cart createCart(UUID productId){
    Cart cart= new Cart();
    cart.getProductIds().add(productId);
    cart.setId(UUID.randomUUID());
    return repository.save(cart);
  }


}
