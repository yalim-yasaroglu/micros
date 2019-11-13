package tr.com.aselsan.uhkks.micro.producs.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.aselsan.uhkks.micro.producs.entities.Product;
import tr.com.aselsan.uhkks.micro.producs.services.ProductService;

@RestController
public class ProductController {

  @Autowired
  ProductService service;

  @GetMapping
  public List<Product> getAllProducts(){
    return service.getAllProducts();
  }

  @PutMapping
  public Product saveProduct(@RequestBody Product p) throws Exception {
    return service.createProduct(p);
  }

  @PostMapping
  public Product updateProduct(@RequestBody Product p) throws Exception {
    return service.editProduct(p);
  }

  @GetMapping("/search")
  public List<Product> searchProduct(@RequestParam(name = "term") String term) throws Exception {
    return service.searchProductByName(term);
  }

  @DeleteMapping("{id}")
  public boolean deleteProduct(@PathVariable("id") UUID id){
    return service.deleteProductById(id);
  }

  @GetMapping("{id}")
  public Product getById(@PathVariable("id") UUID id){
    return service.getProductById(id);
  }



}
