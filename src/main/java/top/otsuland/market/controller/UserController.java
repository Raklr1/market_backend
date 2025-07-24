package top.otsuland.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import top.otsuland.market.common.JwtUtils;
import top.otsuland.market.common.Result;
import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;
import top.otsuland.market.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试 - 获取用户列表
     * @return
     */
    @GetMapping()
    public Result<List<User>> getUsers() {

        List<User> users = userService.getUsersList();
        return new Result<>(users);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        
        String jwt = JwtUtils.geneJWT(user);
        return Result.success(jwt);
    }

    /**
     * 修改基本信息
     */
    @PutMapping("/1")
    public Result<?> edit(@RequestBody User user) {
        boolean success = userService.edit(user);
        if(success) {
            return Result.success(user);
        } else {
            return Result.fail();
        }
    }

    /**
     * 修改个人简介
     */
    @PutMapping("/prof")
    public Result<?> editProf(@RequestBody UserProfile userProfile) {
        boolean success = userService.editProf(userProfile);
        if(success) {
            return Result.success(userProfile);
        } else {
            return Result.fail();
        }
    }
}
