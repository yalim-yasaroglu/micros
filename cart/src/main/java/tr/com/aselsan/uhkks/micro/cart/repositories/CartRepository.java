package tr.com.aselsan.uhkks.micro.cart.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.aselsan.uhkks.micro.cart.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

}
