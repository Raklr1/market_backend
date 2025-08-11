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
    private String prof;

    @TableField
    private Integer sellerId;

    // 有权限的属性
    @TableField
    private Integer state; // 默认为 0 销售中 1 已售罄 2 已下架 3 已删除

    @TableField
    private Integer want; // 默认为 0，显示几人想要。
}