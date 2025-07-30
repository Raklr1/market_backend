package top.otsuland.market.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.entity.Product;

public interface ProductService {

    int add(Integer uid, Product product);
    int pic(Integer kind, Integer uid, Integer pid, MultipartFile pic) throws IOException;
    int picEdit(Integer uid, Integer picId, MultipartFile pic) throws IOException;
    int picDel(Integer uid, Integer picId);
    List<Product> get(Integer uid);
    int edit(Integer uid, Product product);
    int del(Integer uid, Integer pid);
    // int off(Integer pid);
}
