package top.otsuland.market.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.SessionMes;

@Mapper
public interface SessionMesMapper extends BaseMapper<SessionMes> {
    List<SessionMes> selectBySessionId(Integer sesid);
}
