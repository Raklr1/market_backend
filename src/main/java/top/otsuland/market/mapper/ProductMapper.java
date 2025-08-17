package top.otsuland.market.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.otsuland.market.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    Product selectByUidAndPid(Integer uid, Integer pid);
    List<Product> selectByUserId(Integer uid);
    int updateNameById(Integer id, String name);
    int updatePriceById(Integer id, BigDecimal price);
    int updateAmountById(Integer id, Integer amount);
    int updateProfById(Integer id, String prof);
    List<Product> selectByIds(List<Integer> ids);
}
