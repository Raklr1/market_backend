package top.otsuland.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.otsuland.market.common.Result;
import top.otsuland.market.dto.OrderStatusReq;
import top.otsuland.market.entity.Order;
import top.otsuland.market.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PutMapping("/{orderId}")
    public Result<?> editStatus(@PathVariable Integer orderId, @RequestBody OrderStatusReq osr) {
        
        return null;
    }

    @DeleteMapping("/{orderId}")
    public Result<?> delete(@PathVariable Integer orderId) {
        
        return null;
    }

    @GetMapping
    public Result<?> getOrders(@RequestAttribute("id") Integer uid) {
        return null;
    }
}
