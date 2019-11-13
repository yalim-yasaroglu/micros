package tr.com.aselsan.uhkks.micro.producs.entities;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

  @Id
  private UUID id;
  private String name;
  private String description;
  private BigDecimal price;
}
