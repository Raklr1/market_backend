package top.otsuland.market.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.common.PictureUtils;
import top.otsuland.market.dto.ProductMeta;
import top.otsuland.market.entity.Product;
import top.otsuland.market.entity.ProductSecondImages;
import top.otsuland.market.mapper.ProductMapper;
import top.otsuland.market.mapper.ProductSecondImagesMapper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSecondImagesMapper productSecondImagesMapper;

    @Override
    public Product setMeta(Product product, ProductMeta meta) {
        product.setName(meta.getName());
        product.setPrice(meta.getPrice());
        product.setAmount(meta.getAmount());
        product.setSellerId(meta.getId());
        product.setSellerUsername(meta.getUsername());
        // 如果是新添加的商品
        if(product.getId() == null) {
            productMapper.insert(product);
        } else {
            // 如果是修改商品
            productMapper.updateById(product);
        }
        return product;
    }

    @Override
    public Product setMainImage(Product product, MultipartFile mainImage) throws IOException {
        product.setMainImage(mainImage.getBytes());
        product.setMainImageNarrow(PictureUtils.getNarrowPhoto(mainImage));
        return product;
    }

    @Override
    public Product setSecondImages(Product product, MultipartFile... secondImages) throws IOException {
        if(product.getId() == null) {
            productMapper.insert(product);
        }
        for(MultipartFile secondImage : secondImages) {
            if(secondImage != null) {
                ProductSecondImages pro = new ProductSecondImages(product.getId(), secondImage.getBytes());
                productSecondImagesMapper.insert(pro);
            }
        }
        return product;
    }

    @Override
    public void addProduct(ProductMeta meta, MultipartFile mainImage, MultipartFile... secondImages) throws IOException{
        Product product = new Product();
        this.setMeta(product, meta);
        this.setMainImage(product, mainImage);
        productMapper.updateById(product);
        this.setSecondImages(product, secondImages);
    }
}
