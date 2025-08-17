package top.otsuland.market.dto;

import lombok.Data;
import top.otsuland.market.entity.UserProfile;

@Data
public class UserProfResq {
    private Integer userId;
    private String username;
    private String email;
    private String gender;
    private String prof;
    private Integer follow;
    private Integer fans;

    public UserProfResq(UserProfile uprof) {
        this.userId = uprof.getUserId();
        this.email = uprof.getEmail();
        this.gender = uprof.getGender();
        this.prof = uprof.getProf();    
    }
}
