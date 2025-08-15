package top.otsuland.market.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("session_mes")
public class SessionMes {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer sessionId;

    @TableField
    private Integer sid;

    @TableField
    private String content;

    @TableField
    private Timestamp sendTime;
}
