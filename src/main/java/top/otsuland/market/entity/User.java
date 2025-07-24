package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("a_user")
public class User {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField
    private String username;

    @TableField
    private String pwd;
    
    @TableField
    private String tel;

}
