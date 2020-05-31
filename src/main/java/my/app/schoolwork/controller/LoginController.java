package my.app.schoolwork.controller;

import com.alibaba.fastjson.JSONObject;
import my.app.schoolwork.entity.User;
import my.app.schoolwork.repository.UserRepository;
import my.app.schoolwork.service.UserService;
import my.app.schoolwork.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject json){
        String username = (String) json.get("username");
        String password = (String) json.get("password");
        if (userService.login(username,password)) {
            return Result.ok(userService.getUserByName(username),"登录成功");
        } else {
            return Result.error(511, "登录失败");
        }
    }

    @GetMapping("/setup")
    public Result setup(String name, String password, String isManager){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setManager(Boolean.getBoolean(isManager));
        userRepository.save(user);
        return Result.ok();
    }
}
