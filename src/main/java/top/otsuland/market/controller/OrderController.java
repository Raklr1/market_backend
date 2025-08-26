package top.otsuland.market.controller;

import java.net.Inet4Address;
import java.util.List;

import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.otsuland.market.common.Result;
import top.otsuland.market.dto.OrderStatusReq;
import top.otsuland.market.entity.Order;
import top.otsuland.market.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result<?> create(@RequestAttribute("id") Integer uid, @RequestBody Order order) {
        int row = orderService.create(uid, order);
        if(row == -1) {
            return Result.set(-1, "缺少信息！");
        } else if(row == 0) {
            return Result.set(0, "用户不存在！");
        }
        return Result.set(1, "创建成功！", row);
    }

    @PutMapping("/payment/{orderId}")
    public Result<?> editPaymentMethod(@PathVariable Integer orderId, @RequestParam Integer paymentMethod) {
        int row = orderService.pay(orderId, paymentMethod);
        if(row == 1) {
            return Result.set(1, "修改成功！");
        }
        return Result.set(0, "修改失败！");
    }

    @PutMapping("/{orderId}")
    public Result<?> editStatus(@PathVariable Integer orderId, @RequestBody OrderStatusReq osr) {
        int row = orderService.status(orderId, osr);
        if(row == 1) {
            return Result.set(1, "修改成功！");
        }
        return Result.set(0, "修改失败！");
    }

    @GetMapping
    public Result<?> getOrders(@RequestAttribute("id") Integer uid) {
        List<Order> orders = orderService.get(uid);
        if(orders == null || orders.isEmpty()) {
            return Result.set(0, "获取失败！");
        }
        return Result.set(1, "获取成功！", orders);
    }
    
    @PostMapping("/comment/{orderId}")
    public Result<?> comment(@RequestAttribute("id") Integer uid, @RequestBody Order order) {
        //TODO: process POST request
        
        return null;
    }

}
