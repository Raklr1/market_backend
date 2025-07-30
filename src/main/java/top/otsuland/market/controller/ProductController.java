package top.otsuland.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.dto.ProductMeta;
import top.otsuland.market.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> postMethodName(
        @RequestParam Integer id,
        @RequestParam String username,
        @RequestParam String name,
        @RequestParam String price,
        @RequestParam Integer amount,
        @RequestParam("main_image") MultipartFile mainImage,
        @RequestParam(value = "second_image_1", required = false) MultipartFile secondImages1,
        @RequestParam(value = "second_image_2", required = false) MultipartFile secondImages2,
        @RequestParam(value = "second_image_3", required = false) MultipartFile secondImages3,
        @RequestParam(value = "second_image_4", required = false) MultipartFile secondImages4,
        @RequestParam(value = "second_image_5", required = false) MultipartFile secondImages5
    ) {
        ProductMeta meta = new ProductMeta();
        meta.setId(id);
        meta.setUsername(username);
        meta.setName(name);
        meta.setPrice(price);
        meta.setAmount(amount);
        try {
            productService.addProduct(meta, mainImage, secondImages1, secondImages2, secondImages3, secondImages4, secondImages5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    public String requestMethodName(@RequestParam String param) {
        return new String();
    }
    
    public ResponseEntity<?> addProduct() {
        return new ResponseEntity<>(null);
    }

    public ResponseEntity<?> addProduct1() {
        return new ResponseEntity<>(null);
    }
    public ResponseEntity<?> addProduct2() {
        return new ResponseEntity<>(null);
    }
    public ResponseEntity<?> addProduct3() {
        return new ResponseEntity<>(null);
    }
    

}
