package top.otsuland.market.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.common.JwtUtils;
import top.otsuland.market.common.Result;
import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;
import top.otsuland.market.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试 - 获取用户列表
     */
    @GetMapping
    public Result<?> list() {
        List<User> users = userService.getUsersList();
        return Result.success(users);
    }

    /**
     * 注册
     * ok
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        int code = userService.register(user);
        switch(code) {
            case 1: return Result.set(code, "注册成功！");
            case -3: return Result.set(code, "缺少信息！");
            case -1: return Result.set(code, "用户名已经被使用！");
            case -2: return Result.set(code, "该电话号码已经被注册！");
            default: return Result.fail();
        }
    }
    
    /**
     * 登录
     * ok
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        int code = userService.login(user);
        switch(code) {
            case 1: 
                String token = JwtUtils.geneJWT(userService.withId(user));
                return Result.set(code, "登录成功！", token);
            case -1: return Result.set(code, "密码为空！");
            case -2: return Result.set(code, "缺失信息！");
            case -3: return Result.set(code, "用户不存在！");
            case -4: return Result.set(code, "密码不正确！");
            default: return Result.fail();
        }
    }

    /**
     * 修改基本信息
     * ok
     */
    @PutMapping
    public Result<?> meta(@RequestAttribute("id") Integer id, @RequestBody User user) {
        int code = userService.meta(id, user);
        switch(code) {
            case -1: return Result.set(code, "用户不存在！");
            case 1: return Result.set(code, "修改成功！");
            default: return Result.fail();
        }
    }

    /**
     * 修改个人简介
     * ok
     */
    @PutMapping("/prof")
    public Result<?> prof(@RequestAttribute("id") Integer id, @RequestBody UserProfile userProfile) {
        int code = userService.prof(id, userProfile);
        switch(code) {
            case -1: return Result.set(code, "用户不存在！");
            case 1: return Result.set(code, "修改成功！");
            default: return Result.fail();
        }
    }

    /**
     * 上传头像
     * ok
     */
    @PostMapping("/icon/")
    public Result<?> icon(@RequestAttribute("id") Integer id, @RequestParam MultipartFile pic) {
        try {
            int code = userService.icon(id, pic);
            switch (code) {
                case 1: return Result.set(code, "上传成功！");
                case -1: return Result.set(code, "用户不存在！");
                default: return Result.fail();
            }
        } catch (IOException e) {
            return Result.set(-2, "输入输出异常！");
        }
    }

    /**
     * 获取个人简介
     */
    @GetMapping("/prof")
    public Result<?> getProf(@RequestAttribute("id") Integer id) {
        return Result.fail();
    }

    /**
     * 下载头像
     */
    // @GetMapping("/icon/{id}")
    // public ResponseEntity<byte[]> loadIcon2(@PathVariable Integer id) {
    //     byte[] image = fileService.getImageByUserId(id);
    //     final HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_PNG);
    //     return new ResponseEntity<>(image, headers, HttpStatus.OK);
    // }

}
