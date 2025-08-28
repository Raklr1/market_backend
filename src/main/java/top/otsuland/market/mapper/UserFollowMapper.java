package top.otsuland.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import top.otsuland.market.dto.UserFollowVO;
import top.otsuland.market.dto.UserFollowVO2;
import top.otsuland.market.entity.UserFollow;

@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {
    UserFollow selectByFolloweeAndFollower(@Param("followee") Integer followee, @Param("follower") Integer follower);
    Page<UserFollowVO> selectFolloweeWithUsername(
        @Param("page") Page<UserFollowVO> page,
        @Param("uid") Integer uid
    );
    Page<UserFollowVO2> selectFollowerWithUsername(
        @Param("page") Page<UserFollowVO2> page,
        @Param("uid") Integer uid
    );
}
