package my.app.schoolwork.controller;

import my.app.schoolwork.service.ProductService;
import my.app.schoolwork.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/add")
    public Result addProduct(String name, String des, Double price, String url, Integer userId){
        productService.createProduct(name,price,des,url,userId);
        return Result.ok();
    }

    @GetMapping("/product/listCreated")
    public Result listCreated(){
        return Result.ok(productService.findProductByState("created"));
    }

    @GetMapping("/product/release")
    public Result release(Integer id){
        productService.changeState(id,"release");
        return Result.ok();
    }

    @GetMapping("/product/listRelease")
    public Result listRelease(){
        return Result.ok(productService.findProductByState("release"));
    }
}
