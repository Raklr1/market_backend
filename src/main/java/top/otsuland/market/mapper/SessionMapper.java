package top.otsuland.market.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.Session;

@Mapper
public interface SessionMapper extends BaseMapper<Session> {
    Session selectBySidAndRid(@Param("sid") Integer sid,@Param("rid") Integer rid);
    List<Session> selectBySidOrRid(Integer sid);
}
