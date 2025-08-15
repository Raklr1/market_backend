package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("manage_complaint")
public class ManageComplaint {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer orderId;

    @TableField
    private Integer buyerId;

    @TableField
    private Integer sellerId;

    @TableField
    private Integer type;

    @TableField
    private String reason;
}
