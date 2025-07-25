package top.otsuland.market.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.PicIcon;

@Mapper
public interface PicIconMapper extends BaseMapper<PicIcon> {

    Optional<PicIcon> selectByUserId(int userId);
}
