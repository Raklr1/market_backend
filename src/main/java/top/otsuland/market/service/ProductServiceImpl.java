package top.otsuland.market.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import top.otsuland.market.dto.ProductCreateReq;
import top.otsuland.market.entity.Product;
import top.otsuland.market.entity.ProductFav;
import top.otsuland.market.entity.ProductPic;
import top.otsuland.market.mapper.ProductCategoryMapper;
import top.otsuland.market.mapper.ProductFavMapper;
import top.otsuland.market.mapper.ProductMapper;
import top.otsuland.market.mapper.ProductPicMapper;
import top.otsuland.market.mapper.UserMapper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductPicMapper productPicMapper;

    @Autowired
    private ProductFavMapper productFavMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    /**
     * 添加商品
     */
    @Override
    public int add(Integer uid, ProductCreateReq pcr) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 信息不完整
        if(StringUtils.isBlank(pcr.getName()) ||
            pcr.getAmount() == null ||
            pcr.getPrice() == null) {
            return -2;
        }
        Product product = new Product();
        product.setName(pcr.getName());
        product.setPrice(pcr.getPrice());
        product.setAmount(pcr.getAmount());
        product.setProf(pcr.getProf());
        product.setSellerId(uid);
        product.setSellerName(userMapper.selectUsernameById(uid));
        product.setState(0);
        product.setWant(0);
        productMapper.insert(product);
        return 1;
    }

    /**
     * 上传商品图
     */
    @Override
    public int pic(Integer kind, Integer uid, Integer pid, MultipartFile pic) throws IOException {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 无权限或商品不存在！
        if(productMapper.selectByUidAndPid(uid, pid) == null) {
            return -2;
        }
        // 主图已经上传的情况
        if(kind == 1) {
            // 已经上传过主图
            if(productPicMapper.selectMainByPid(pid) != null) {
                return -3;
            }
        }
        // 副图超过 5 张的情况
        if(productPicMapper.countSubByPid(pid) >= 5) {
            return -4;
        }
        ProductPic picbody = new ProductPic();
        picbody.setPicture(pic.getBytes());
        picbody.setKind(kind);
        picbody.setProductId(pid);
        productPicMapper.insert(picbody);
        return 1;
    }

    /**
     * 修改商品图
     */
    @Override
    public int picEdit(Integer uid, Integer picId, MultipartFile pic) throws IOException {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 无权限或商品图不存在！
        if(productPicMapper.selectByUidAndId(uid, picId) == null) {
            return -2;
        }
        productPicMapper.updatePicById(picId, pic.getBytes());
        return 1;
    }

    /**
     * 删除商品图（只能删副图）
     */
    @Override
    public int picDel(Integer uid, Integer picId) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 无权限或商品图不存在！
        if(productPicMapper.selectByUidAndId(uid, picId) == null) {
            return -2;
        }
        // 禁止删主图
        if(productPicMapper.selectById(picId).getKind() == 1) {
            return -3;
        }
        productPicMapper.deleteById(picId);
        return 1;
    }


    /**
     * 获取用户发布的商品列表
     */
    @Override
    public List<Product> get(Integer uid) {
        return productMapper.selectByUserId(uid);
    }

    /**
     * 修改商品
     */
    @Override
    public int edit(Integer uid, Product product) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 无权限或商品不存在！
        if(productMapper.selectByUidAndPid(uid, product.getId()) == null) {
            return -2;
        }
        int id = product.getId();
        // 修改商品名
        if(!StringUtils.isBlank(product.getName())) {
            productMapper.updateNameById(id, product.getName());
        }
        // 修改价格
        if(product.getPrice() == null) {
            productMapper.updatePriceById(id, product.getPrice());
        }
        // 修改数量
        if(product.getAmount() != null) {
            productMapper.updateAmountById(id, product.getAmount());
        }
        // 修改商品描述
        if(!StringUtils.isBlank(product.getProf())) {
            productMapper.updateProfById(id, product.getProf());
        }
        // 修改成功！
        return 1;
    }

    /**
     * 删除商品
     */
    @Override
    public int del(Integer uid, Integer pid) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 无权限或商品不存在！
        if(productMapper.selectByUidAndPid(uid, pid) == null) {
            return -2;
        }
        // 成功删除！
        productMapper.deleteById(pid);
        return 1;
    }

    /**
     * 收藏商品
     */
    @Override
    public int fav(Integer uid, Integer pid) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 商品不存在！
        if(productMapper.selectById(pid) == null) {
            return -2;
        }
        // 已经收藏过该商品了！
        if(productFavMapper.selectByUidAndPid(uid, pid) != null) {
            return -3;
        }
        ProductFav productFav = new ProductFav();
        productFav.setPid(pid);
        productFav.setUid(uid);
        productFavMapper.insert(productFav);
        return 1;
    }

    /**
     * 取消收藏商品
     */
    @Override
    public int favDel(Integer uid, Integer pid) {
        ProductFav pf = productFavMapper.selectByUidAndPid(uid, pid);
        // 收藏不存在！
        if(pf == null) {
            return -1;
        }
        productFavMapper.deleteById(pf.getId());
        return 1;
    }

    /**
     * 获取收藏列表
     * ok
     */
    @Override
    public List<Product> favList(Integer uid) {
        List<Integer> ids = productFavMapper.selectIdsByUid(uid);
        if( ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Product> ps = productMapper.selectByIds(ids);
        return ps;
    }

    /**
     * 获取商品的主图
     */
    @Override
    public byte[] getMainPic(Integer pid) {
        if((pid == null) || (productPicMapper.selectMainByPid(pid) == null)) {
            return null;
        }
        return productPicMapper.selectMainByPid(pid).getPicture();
    }

    /**
     * 获取商品的副图
     */
    @Override
    public List<ProductPic> getSubPic(Integer pid) {
        if(pid == null) {
            return null;
        }
        return productPicMapper.selectSubByPid(pid);
    }

    /**
     * 获取商品列表
     */
    @Override
    public Page<Product> list(
        Page<Product> pageParam, Integer uid, List<Integer> categoryIds,
        Integer priceSort, Integer timeSort, String keyword
    ) {
        // 构建查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        // 商品未删除
        queryWrapper.eq(Product::getState, Arrays.asList(2, 3));
        // 分类筛选
        if(categoryIds != null && !categoryIds.isEmpty()) {
            List<Integer> productIds = productCategoryMapper.selectProductIdsByCategoryIds(categoryIds);
            if(productIds.isEmpty()) {
                return new Page<>(pageParam.getCurrent(), pageParam.getSize(), 0);
            }
            queryWrapper.in(Product::getId, productIds);
        }
        // 关键词搜索
        if(org.springframework.util.StringUtils.hasText(keyword)) {
            // 模糊匹配用户名搜索
            if(keyword.startsWith("@")) {
                String username = keyword.substring(1);
                queryWrapper.like(Product::getSellerName, username);
            } else {
                queryWrapper.and(wrapper -> wrapper
                    .like(Product::getName, keyword)
                    .or()
                    .like(Product::getProf, keyword)
                );
            }
            
        }
        // 处理排序
        if(priceSort != null) {
            queryWrapper.orderBy(true, priceSort == 1, Product::getPrice);
        }
        if(timeSort != null) {
            queryWrapper.orderBy(true, timeSort == 1, Product::getCreateTime);
        }
        // 执行分页查询
        return productMapper.selectPage(pageParam, queryWrapper);
    }
}
