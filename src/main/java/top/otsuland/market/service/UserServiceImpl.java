package top.otsuland.market.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.common.PictureUtils;
import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserPic;
import top.otsuland.market.entity.UserProfile;
import top.otsuland.market.mapper.UserMapper;
import top.otsuland.market.mapper.UserPicMapper;
import top.otsuland.market.mapper.UserProfileMapper;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserPicMapper userPicMapper;

    @Override
    public List<User> getUsersList() {
        return userMapper.list();
    }

    /**
     * 注册
     * ok
     */
    @Override
    public int register(User user) {
        // 检测是否为空、空串、空白串
        if(StringUtils.isAnyBlank(user.getPwd(), user.getTel(), user.getUsername())) {
            return -3;
        }
        // 如果用户名已经被使用
        if(userMapper.selectByUsername(user.getUsername()) != null) {
            // 用户名已经被使用！
            return -1;
        }
        if(userMapper.selectByTel(user.getTel()) != null) {
            // 该电话号码已经被注册！
            return -2;
        }
        int row = userMapper.insert(user);
        if(row <= 0) {
            return 0;
        }
        return 1;
    }

    /**
     * 登录
     * ok
     */
    @Override
    public int login(User user) {
        if(StringUtils.isAnyBlank(user.getPwd())) {
            // 密码为空！
            return -1;
        }
        if(StringUtils.isAllBlank(user.getUsername(), user.getTel())) {
            // 缺失信息！
            return -2;
        }
        User u = null;
        // 输入了用户名
        if(StringUtils.isBlank(user.getTel())) {
            u = userMapper.selectByUsername(user.getUsername());
            if(u == null) {
                // 用户不存在！
                return -3;
            }
        } else { // 输入了电话号码
            u = userMapper.selectByTel(user.getTel());
            if(u == null) {
                return -3;
            }
        }
        // 密码不正确！
        // 字符串比较，不能直接 !=,!= 比较的是其引用！
        if(!u.getPwd().equals(user.getPwd())) {
            return -4;
        }
        // 登录成功！
        return 1;
    }

    /**
     * 获取 user 对象的 id
     * ok
     */
    @Override
    public User withId(User user) {
        if(StringUtils.isBlank(user.getTel())) {
            return userMapper.selectByUsername(user.getUsername());
        }
        return userMapper.selectByTel(user.getTel());
    }

    /**
     * 修改基本信息
     * ok
     */
    @Override
    public int meta(Integer id, User user) {
        // 用户不存在
        if(userMapper.selectById(id) == null) {
            return -1;
        }
        // 修改密码
        if(!StringUtils.isBlank(user.getPwd())) {
            userMapper.updatePwdById(id, user.getPwd());
        }
        // 修改电话号码
        if(!StringUtils.isBlank(user.getTel())) {
            userMapper.updateTelById(id, user.getTel());
        }
        // 修改用户名
        if(!StringUtils.isBlank(user.getUsername())) {
            userMapper.updateUsernameById(id, user.getUsername());
        }
        // 修改成功
        return 1;
    }

    /**
     * 修改个人简介
     * ok
     */
    @Override
    public int prof(Integer uid, UserProfile userProfile) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 获取 user_profile id
        int id = userProfileMapper.selectIdByUserId(uid);
        // 修改邮箱
        if(!StringUtils.isBlank(userProfile.getEmail())) {
            userProfileMapper.updateEmailById(id, userProfile.getEmail());
        }
        // 修改性别
        if((!StringUtils.isBlank(userProfile.getGender()))
            && ((userProfile.getGender().equals("男"))
            || (userProfile.getGender().equals("女"))
            || (userProfile.getGender().equals("未公开")))) {
            userProfileMapper.updateGenderById(id, userProfile.getGender());
        }
        // 修改简介文字
        if(!StringUtils.isBlank(userProfile.getProf())) {
            userProfileMapper.updateProfById(id, userProfile.getProf());
        }
        // 修改成功
        return 1;
    }

    @Override
    public UserProfile getProf(Integer uid) {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return null;
        }
        return userProfileMapper.selectByUid(uid);
    }

    /**
     * 上传头像图片
     * ok
     */
    @Override
    public int icon(Integer uid, MultipartFile pic) throws IOException {
        // 用户不存在！
        if(userMapper.selectById(uid) == null) {
            return -1;
        }
        // 生成缩略图
        byte[] picNarrow = PictureUtils.getNarrowPhoto(pic);
        // 如果用户没有头像
        if(userPicMapper.selectByUserId(uid) == null) {
            userPicMapper.insertPicAndPicNarrow(uid, pic.getBytes(), picNarrow);
        }
        // 如果用户已经有头像
        int id = userPicMapper.selectIdByUserId(uid);
        userPicMapper.updatePicById(id, pic.getBytes());
        // 上传成功！
        return 1;
    }

    @Override
    public byte[] getIcon(Integer uid) {
        UserPic upic = userPicMapper.selectByUserId(uid);
        if(upic == null) {
            return null;
        }
        return upic.getPicture();
    }
}
