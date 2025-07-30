package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("product")
public class Product {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String name;

    @TableField
    private String price; // TODO 学习 decimalformat

    @TableField
    private Integer amount;

    @TableField
    private Integer sellerId;

    @TableField
    private String sellerUsername;

    @TableField
    private byte[] mainImage;

    @TableField
    private byte[] mainImageNarrow;
}
