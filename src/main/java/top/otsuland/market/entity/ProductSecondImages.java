package top.otsuland.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("product_second_images")
public class ProductSecondImages {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer productId;

    @TableField
    private byte[] secondImage;

    public ProductSecondImages(Integer productId, byte[] secondImage) {
        this.productId = productId;
        this.secondImage = secondImage;
    }
}
