package top.otsuland.market.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
