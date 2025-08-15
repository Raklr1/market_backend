package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("session")
public class Session {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer aId;

    @TableField
    private Integer bId;

    @TableField
    private String aUsername;

    @TableField
    private String bUsername;

}
