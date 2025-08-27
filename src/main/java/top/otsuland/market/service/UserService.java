package top.otsuland.market.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.dto.UserFollowResp;
import top.otsuland.market.dto.UserMetaResp;
import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;

public interface UserService {

    List<User> getUsersList();

    int register(User user);
    int login(User user);
    User withId(User user);
    int meta(Integer id, User user);
    UserMetaResp getMeta(Integer uid);
    int prof(Integer id, UserProfile userProfile);
    UserProfile getProf(Integer uid);
    int icon(Integer id, MultipartFile pic) throws IOException;
    byte[] getIcon(Integer uid);
    int follow(Integer uid, Integer fid);
    int disfollow(Integer uid, Integer fid);
    // 获取关注列表
    List<UserFollowResp> getFollowee(Integer uid);
    // 获取粉丝列表
    List<UserFollowResp> getFollower(Integer uid);
}
