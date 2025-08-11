package top.otsuland.market.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.ProductFav;

@Mapper
public interface ProductFavMapper extends BaseMapper<ProductFav> {

    ProductFav selectByUidAndPid(@Param("pid") Integer pid, @Param("uid") Integer uid);
    List<ProductFav> selectByUid(Integer uid);
    List<Integer> selectIdsByUid(Integer uid);
}
