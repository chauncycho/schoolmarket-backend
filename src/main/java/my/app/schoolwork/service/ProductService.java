package my.app.schoolwork.service;

import my.app.schoolwork.entity.Product;
import my.app.schoolwork.entity.User;
import my.app.schoolwork.repository.ProductRepository;
import my.app.schoolwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public boolean productSave(Product product){
        productRepository.save(product);
        return true;
    }

    public boolean createProduct(String name, Double price, String des, String imagePath, Integer userId){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDes(des);
        product.setPrice(price);
        product.setPicturePath(imagePath);
        product.setState("created");

        Optional<User> userFindResult = userRepository.findById(userId);
        product.setFounder(userFindResult.get());

        productRepository.save(product);
        return true;
    }

    public List<Product> findProductByState(String state){
        return productRepository.findByState(state);
    }

    public void changeState(Integer id, String state){
        Product product = productRepository.findById(id).get();
        product.setState(state);
        productRepository.save(product);
    }
}
