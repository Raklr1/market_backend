package top.otsuland.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;
import top.otsuland.market.mapper.UserMapper;
import top.otsuland.market.mapper.UserProfileMapper;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public List<User> getUsersList() {
        return userMapper.list();
    }

    @Override
    public boolean regist(User user) {
        int row = userMapper.insert(user);
        if(row <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(User user) {
        int row = userMapper.updateById(user);
        if(row <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean editProf(UserProfile userProfile) {
        // 因为个人信息会随着用户的创建而创建，且不提供删除个人信息的接口，所以无需验证是否存在，用户只有修改一种选择 -_-
        int userId = userProfile.getUserId();
        UserProfile up = userProfileMapper.selectByUserId(userId)
                .orElse(null);
        if(up == null) {
            return false;
        }
        userProfile.setId(up.getId());
        int row = userProfileMapper.updateById(userProfile);
        if(row <= 0) {
            return false;
        }
        return true;
    }

}
