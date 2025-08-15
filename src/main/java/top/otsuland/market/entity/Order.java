package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer number;

    @TableField
    private Integer status;

    @TableField
    private Integer buyerId;

    @TableField
    private String buyerUsername;

    @TableField
    private String buyerTel;

    @TableField
    private Integer sellerId;

    @TableField
    private String sellerUsername;

    @TableField
    private String sellerTel;

    @TableField
    private Integer paymentMethod;

    @TableField
    private String note;

    @TableField
    private String comment;
}
