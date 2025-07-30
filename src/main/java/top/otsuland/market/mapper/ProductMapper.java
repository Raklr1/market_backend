package top.otsuland.market.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
