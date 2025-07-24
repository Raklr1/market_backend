package top.otsuland.market.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.UserProfile;

@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {
    
    Optional<UserProfile> selectByUserId(int userId);
}
