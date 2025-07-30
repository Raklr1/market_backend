package top.otsuland.market.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.ProductPic;

@Mapper
public interface ProductPicMapper extends BaseMapper<ProductPic> {
    ProductPic selectByPid(Integer pid);
    ProductPic selectMainByPid(Integer pid);
    Integer countSubByPid(Integer pid);
    ProductPic selectByUidAndId(@Param("uid") Integer uid, @Param("id") Integer id);
    int updatePicById(@Param("id") Integer id, @Param("pic") byte[] pic);
}
