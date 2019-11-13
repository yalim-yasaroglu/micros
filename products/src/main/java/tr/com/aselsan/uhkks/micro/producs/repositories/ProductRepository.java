package tr.com.aselsan.uhkks.micro.producs.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.aselsan.uhkks.micro.producs.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

  public List<Product> findByNameContains(String name);
}
