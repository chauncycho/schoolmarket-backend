package my.app.schoolwork.service;

import my.app.schoolwork.entity.Product;
import my.app.schoolwork.entity.ProductOrder;
import my.app.schoolwork.entity.User;
import my.app.schoolwork.repository.ProductOrderRepository;
import my.app.schoolwork.repository.ProductRepository;
import my.app.schoolwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Transactional
    public boolean makeOrder(Integer userId, Integer productId){
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setUser(user);
        productOrder.setProduct(product);

        productService.changeState(productId,"finished");

        productOrderRepository.save(productOrder);

        return true;
    }

    public List<ProductOrder> findAllOrderByUserId(Integer userId){
        return productOrderRepository.findAllByUserId(userId);
    }

    public boolean comment(Integer id, String comment){
        ProductOrder order = productOrderRepository.findById(id).get();
        order.setOrderComment(comment);
        productOrderRepository.save(order);
        return true;
    }
}
