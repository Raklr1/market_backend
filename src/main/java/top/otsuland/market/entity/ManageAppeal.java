package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("manage_appeal")
public class ManageAppeal {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer complaintId;

    @TableField
    private String reason;
}
