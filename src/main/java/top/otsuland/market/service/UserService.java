package top.otsuland.market.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;

public interface UserService {

    List<User> getUsersList();

    int register(User user);
    int login(User user);
    User withId(User user);
    int meta(Integer id, User user);
    int prof(Integer id, UserProfile userProfile);
    int icon(Integer id, MultipartFile pic) throws IOException;
}
