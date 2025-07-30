package top.otsuland.market.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.common.Result;
import top.otsuland.market.entity.Product;
import top.otsuland.market.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 用户-添加商品
     */
    @PostMapping("/{uid}")
    public Result<?> add(@PathVariable Integer uid, @RequestBody Product product) {
        int code = productService.add(uid, product);
        switch(code) {
            case 1: return Result.set(code, "上传成功！");
            case -1: return Result.set(code, "用户不存在！");
            case -2: return Result.set(code, "信息不完整！");
            default: return Result.fail();
        }
    }

    /**
     * 用户-上传商品图
     */
    @PostMapping("/pic/{kind}/{uid}/{pid}")
    public Result<?> pic(
        @PathVariable Integer kind, @PathVariable Integer uid, 
        @PathVariable Integer pid, MultipartFile pic) {
        try {
            int code = productService.pic(kind, uid, pid, pic);
            switch(code) {
                case 1: return Result.set(code, "上传成功！");
                case -1: return Result.set(code, "用户不存在！");
                case -2: return Result.set(code, "无权限或商品不存在！");
                default: return Result.fail();
            }
        } catch (IOException e) {
            return Result.set(-3, "输入输出异常！");
        }

    }

    /**
     * 用户-修改商品图
     */
    @PutMapping("/pic/{uid}/{picId}")
    public Result<?> picEdit(@PathVariable Integer uid, @PathVariable Integer picId, MultipartFile pic) {
        try {
            int code = productService.picEdit(uid, picId, pic);
            switch(code) {
                case 1: return Result.set(code, "修改成功！");
                case -1: return Result.set(code, "用户不存在！");
                case -2: return Result.set(code, "无权限或商品图不存在！");
                default: return Result.fail();
            }
        } catch (IOException e) {
            return Result.set(-3, "输入输出异常！");
        }
        
    }

    /**
     * 删除商品图
     */
    @DeleteMapping("pic/{uid}/{picId}")
    public Result<?> picDel(@PathVariable Integer uid, @PathVariable Integer picId) {
        int code = productService.picDel(uid, picId);
        switch(code) {
            case 1: return Result.set(code, "上传成功！");
            case -1: return Result.set(code, "用户不存在！");
            case -2: return Result.set(code, "无权限或商品不存在！");
            default: return Result.fail();
        }
    }
    
    /**
     * 用户-获取自己已发布的商品列表
     */
    @GetMapping("/{uid}")
    public Result<?> get(@PathVariable Integer uid) {
        List<Product> products = productService.get(uid);
        if(products == null) {
            return Result.set(-1, "暂无商品！");
        }
        return Result.set(1, "获取商品成功！", products);
    }

    /**
     * 用户-修改商品
     */
    @PutMapping("/{uid}")
    public Result<?> edit(@PathVariable Integer uid, @RequestBody Product product) {
        int code = productService.edit(uid, product);
        switch(code) {
            case 1: return Result.set(code, "修改成功！");
            case -1: return Result.set(code, "用户不存在！");
            case -2: return Result.set(code, "无权限或商品不存在！");
            default: return Result.fail();
        }
    }

    /**
     * 用户-删除商品（不再出售）
     */
    @DeleteMapping("/{uid}/{pid}")
    public Result<?> del(@PathVariable Integer uid, @PathVariable Integer pid) {
        int code = productService.del(uid, pid);
        switch(code) {
            case 1: return Result.set(code, "删除成功！");
            case -1: return Result.set(code, "用户不存在！");
            case -2: return Result.set(code, "无权限或商品不存在！");
            default: return Result.fail();
        }
    }

    /**
     * 分页获取商品列表
     */
    @GetMapping
    public Result<?> list(
        @RequestParam(required = true) Integer page,
        @RequestParam(required = true) Integer size,
        @RequestParam String[] category, // 分类
        @RequestParam Integer price,
        @RequestParam Integer time
    ) {

        return Result.fail();
    }
    
}
