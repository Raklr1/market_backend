package top.otsuland.market.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.dto.ProductMeta;
import top.otsuland.market.entity.Product;

public interface ProductService {
    Product setMeta(Product product, ProductMeta meta);
    Product setMainImage(Product product, MultipartFile mainImage) throws IOException;
    Product setSecondImages(Product product, MultipartFile...secondImages) throws IOException;
    void addProduct(ProductMeta meta, MultipartFile mainImage, MultipartFile...secondImages) throws IOException;
}
