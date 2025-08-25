package top.otsuland.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import top.otsuland.market.dto.OrderStatusReq;
import top.otsuland.market.entity.Order;
import top.otsuland.market.entity.Product;
import top.otsuland.market.mapper.OrderMapper;
import top.otsuland.market.mapper.ProductMapper;
import top.otsuland.market.mapper.UserMapper;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 创建订单
     */
    @Override
    @Transactional
    public int create(Integer uid, Order or) {
        if(userMapper.selectById(uid) == null) {
            return 0;
        }
        // 验证参数
        if(or.getProductId() == null ||
            or.getUnitPrice() == null ||
            or.getProductAmount() == null ||
            or.getSellerId() == null ||
            or.getBuyerId() == null ||
            or.getPaymentMethod() == null ||
            or.getTotalPrice() == null
        ) {
            return -1;
        }
        // 减少商品库存
        LambdaUpdateWrapper<Product> productWrapper = new LambdaUpdateWrapper<>();
        productWrapper.eq(Product::getId, or.getProductId())
            .gt(Product::getAmount, 0)
            .setSql("amount = amount - {0}", or.getProductAmount())
            .setSql("state = CASE WHEN (amount - {0}) <= 0 THEN 1 ELSE state END", or.getProductAmount());
        int productRows = productMapper.update(null, productWrapper);
        if(productRows == 0) {
            // 已售罄！
            return -1;
        }
        // 成功创建
        or.setState(0);
        or.setProductName(productMapper.selectNameById(or.getProductId()));
        or.setSellerName(userMapper.selectUsernameById(or.getSellerId()));
        or.setBuyerName(userMapper.selectUsernameById(or.getBuyerId()));
        int row = orderMapper.insert(or);
        return row;
    }

    @Override
    public int status(Integer orderId, OrderStatusReq osr) {
        Order order = orderMapper.selectById(orderId);
        if(order == null) {
            return -1;
        }

        LambdaUpdateWrapper<Order> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Order::getId, orderId);
        boolean hasUpdate = false;

        // 0 未支付 -> 1 已支付
        if(order.getState() == 0 && osr.getStatus() == 1) {
            wrapper.set(Order::getState, 1);
            hasUpdate = true;
        }
        // 1 已支付 -> 2 商家已发货
        if(order.getState() == 1 && osr.getStatus() == 2) {
            wrapper.set(Order::getState, 2);
            hasUpdate = true;
        }
        // 2 商家已发货 -> 3 商家已送达
        if(order.getState() == 2 && osr.getStatus() == 3) {
            wrapper.set(Order::getState, 3);
            hasUpdate = true;
        }
        // 3 商家已送达 -> 4 买家确认收货
        if(order.getState() == 3 && osr.getStatus() == 4) {
            wrapper.set(Order::getState, 4);
            hasUpdate = true;
        }
        // 0 -> 5 已取消
        if(order.getState() == 0 && osr.getStatus() == 5) {
            wrapper.set(Order::getState, 5);
            hasUpdate = true;
        }
        // 6 已退款
        if(order.getState() >= 1 && osr.getStatus() == 6) {
            wrapper.set(Order::getState, 6);
            hasUpdate = true;
        }
        // 2/3 -> 7 已退货
        if((order.getState() == 2 || order.getState() == 3) && osr.getStatus() == 7) {
            wrapper.set(Order::getState, 7);
            hasUpdate = true;
        }
        if(!hasUpdate) {
            return 0;
        }
        int rows = orderMapper.update(null, wrapper);
        if(rows > 0) {
            return 1;
        }
        return -2;
    }

    // @Override
    // public int delete(Integer uid, Integer oid) {
        
    //     return 0;
    // }

    @Override
    public List<Order> get(Integer uid) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.nested(wq -> wq
            .eq(Order::getBuyerId, uid)
            .or()
            .eq(Order::getSellerId, uid)
        );
        return orderMapper.selectList(wrapper);
    }
}
