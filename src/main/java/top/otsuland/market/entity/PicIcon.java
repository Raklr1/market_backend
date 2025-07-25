package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("pic_icon")
public class PicIcon {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField
    private int userId;

    @TableField
    private String name;

    @TableField
    private byte[] picture;

    @TableField
    private byte[] pictureNarrow;

}
