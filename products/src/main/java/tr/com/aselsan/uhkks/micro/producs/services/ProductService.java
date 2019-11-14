package tr.com.aselsan.uhkks.micro.producs.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.aselsan.uhkks.micro.producs.entities.Product;
import tr.com.aselsan.uhkks.micro.producs.repositories.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository repo;

  public List<Product> getAllProducts(){
    return repo.findAll();
  }

  public Product createProduct(Product product) throws Exception {
    if(product.getId()!=null){
      throw new Exception("Product id should be empty!");
    }
    product.setId(UUID.randomUUID());
    return repo.save(product);
  }

  public Product editProduct(Product product) throws Exception {
    if(product.getId()==null){
      throw new Exception("Product id should NOT be empty!");
    }

    return repo.save(product);
  }

  public List<Product> searchProductByName(String name){
    return repo.findByNameContains(name);
  }

  public boolean deleteProductById(UUID id){
    repo.deleteById(id);
    return true;
  }

  public Optional<Product> getProductById(UUID id) {
    return repo.findById(id);
  }
}
