package my.app.schoolwork.controller;

import my.app.schoolwork.service.OrderService;
import my.app.schoolwork.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/makeOrder")
    public Result makeOrder(Integer userId, Integer productId){
        if (orderService.makeOrder(userId,productId)){
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @GetMapping("/order/findByUserId")
    public Result findAllOrderByUserId(Integer userId){
        return Result.ok(orderService.findAllOrderByUserId(userId));
    }
}
