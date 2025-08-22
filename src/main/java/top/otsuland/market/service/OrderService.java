package top.otsuland.market.service;

import java.util.List;

import top.otsuland.market.dto.OrderStatusReq;
import top.otsuland.market.entity.Order;

public interface OrderService {
    int create(Integer uid, Order or);
    int status(Integer orderId, OrderStatusReq osr);
    // int delete(Integer uid, Integer oid);
    List<Order> get(Integer uid);
}
