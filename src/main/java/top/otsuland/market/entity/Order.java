package top.otsuland.market.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

// order 是保留字
@Data
@TableName("a_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;

    // // 订单号，区别于 id，暴露给用户
    // @TableField
    // private Integer number;

    @TableField
    private Integer productId;

    @TableField
    private String productName;

    @TableField
    private BigDecimal unitPrice;

    @TableField
    private Integer productAmount;

    @TableField
    private Integer sellerId;

    @TableField
    private String sellerName;

    @TableField
    private Integer buyerId;

    @TableField
    private String buyerName;

    @TableField
    private Integer paymentMethod;

    @TableField
    private BigDecimal totalPrice;

    // 备注信息
    @TableField
    private String note;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 0 未支付 1 已支付 2 商家已发货 3 商家已送达 4 买家确认收货
    @TableField
    private Integer state;

    // 后期添加的内容
    @TableField
    private String buyerComment;

    @TableField
    private LocalDateTime buyerCommentTime;

    @TableField
    private String sellerComment;

    @TableField
    private LocalDateTime sellerCommentTime;

}
