package top.otsuland.market.dto;

import lombok.Data;

@Data
public class UserLoginResp {
    private String token;
    private Integer uid;
}
