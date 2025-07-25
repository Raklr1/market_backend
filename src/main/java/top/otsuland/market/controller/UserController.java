package top.otsuland.market.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.common.JwtUtils;
import top.otsuland.market.common.Result;
import top.otsuland.market.entity.User;
import top.otsuland.market.entity.UserProfile;
import top.otsuland.market.service.FileService;
import top.otsuland.market.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

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

    /**
     * 上传个人头像
     * @param userId
     * @param name
     * @param picture
     * @return
     */
    @PostMapping("/icon")
    public Result<?> saveIcon(
        @RequestParam(name = "user_id") int userId,
        @RequestParam(required = false) String name,
        @RequestParam(required = true) MultipartFile picture) {
        System.out.println("userId: " + userId);
        System.out.println("name: " + name);
        System.out.println("file name: " + picture.getOriginalFilename());
        try {
            fileService.saveIcon(userId, name, picture);
            // 返回文件的元数据
            Map<String, Object> fileInfo = Map.of(
                "name", picture.getOriginalFilename(),
                "size", picture.getSize(),
                "contenType", picture.getContentType()
            );
            return Result.success(fileInfo);
        } catch (Exception e) {
            return Result.error("fail");
        }
    }

    @GetMapping("/icon/1")
    public Result<?> loadIcon(@RequestParam int id) {

        return Result.success(null);
    }
    
}
