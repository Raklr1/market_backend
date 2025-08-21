package top.otsuland.market.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderReq {
    private Integer productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer productAmount;
    private Integer sellerId;
    private String sellerName;
    private Integer buyerId;
    private String buyerName;
    private Integer paymentMethod;
    private BigDecimal totalPrice;
    // 不是必要的
    private String note;
}
