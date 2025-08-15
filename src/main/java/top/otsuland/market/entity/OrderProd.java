package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("order_prod")
public class OrderProd {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer orderId;

    @TableField
    private Integer productId;

    @TableField
    private Integer quantity;

    @TableField
    private Integer unitPrice;
    
}
