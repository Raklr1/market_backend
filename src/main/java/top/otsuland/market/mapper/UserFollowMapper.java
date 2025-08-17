package top.otsuland.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.UserFollow;

@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {
    UserFollow selectByFolloweeAndFollower(@Param("followee") Integer followee, @Param("follower") Integer follower);
}
