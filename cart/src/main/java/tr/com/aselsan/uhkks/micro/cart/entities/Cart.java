package tr.com.aselsan.uhkks.micro.cart.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cart {
  @Id
  private UUID id;
}
