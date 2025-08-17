package top.otsuland.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.otsuland.market.common.Result;
import top.otsuland.market.dto.OrderReq;
import top.otsuland.market.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result<?> createOrder(@RequestAttribute("id") Integer uid, @RequestBody OrderReq orderReq ) {
        
        
        return null;
    }

    @PostMapping("/products")
    public Result<?> addProduct(@RequestBody String entity) {
        //TODO: process POST request
        
        return null;
    }

    @PutMapping("/{id}")
    public Result<?> status(@PathVariable String id, @RequestBody String entity) {
        
        return null;
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteOrder() {
        return null;
    }

    @DeleteMapping("/products/{id}")
    public Result<?> deleteProduct() {
        return null;
    }

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/{orderId}")
    public Result<?> get(@RequestParam String param) {
        return null;
    }

    @GetMapping("/users")
    public Result<?> user(@RequestAttribute("id") Integer uid) {
        return null;
    }
    
    
    
    
    


}
