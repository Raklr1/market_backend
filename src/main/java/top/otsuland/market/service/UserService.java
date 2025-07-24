package top.otsuland.market.service;

import java.util.List;

import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;

public interface UserService {

    List<User> getUsersList();

    boolean regist(User user);

    boolean edit(User user);

    boolean editProf(UserProfile userProfile);
}
