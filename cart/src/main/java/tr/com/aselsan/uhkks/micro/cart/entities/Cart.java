package tr.com.aselsan.uhkks.micro.cart.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cart {

  @Id
  private UUID id;

  @ElementCollection(targetClass=UUID.class)
  private List<UUID> productIds = new ArrayList<UUID>();


}