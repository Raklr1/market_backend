package top.otsuland.market.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取用户列表
     * @return
     */
    List<User> list();

}
