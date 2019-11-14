package tr.com.aselsan.uhkks.micro.cart.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.aselsan.uhkks.micro.cart.entities.Cart;
import tr.com.aselsan.uhkks.micro.cart.services.CartService;

@RestController
public class CartController {

  @Autowired
  private CartService service;

  @PutMapping("{id}")
  public Cart createCart(@PathVariable("id") UUID productId){
    return service.createCart(productId);
  }
  /*@PostMapping
  public Cart addProductToCart(UUID productId, UUID cartId){
    return null;
  }
  @PostMapping
  public Cart removeProductFromCart(UUID productId, UUID cartId){
    return null;
  }
  @PutMapping
  public boolean buy(UUID id){
    return false;
  }*/

  @GetMapping
  public List<Cart> getCarts(){
    return service.getAllCarts();
  }
  @GetMapping("{id}")
  public Cart getCartByID(@PathVariable("id") UUID id){
    return service.getCartById(id);
  }
  @DeleteMapping("{id}")
  public boolean deleteCartById( @PathVariable("id")UUID id){
    return service.deleteCartById(id);
  }

}
