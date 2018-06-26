package com.tangzhe.controller;

import com.tangzhe.entity.LoginInfo;
import com.tangzhe.entity.User;
import com.tangzhe.service.UserService;
import com.tangzhe.util.LoginInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 唐哲
 * 2018-06-24 18:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/add")
    public String add() {
        User user = new User();
        user.setUsername("laowang");
        user.setPassword("666666");
        userService.save(user);
        return "success";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id) {
        User user = userService.findById(id);
        user.setUsername("laowang666");
        userService.update(user);
        return "success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "success";
    }

    @GetMapping("/list")
    public List<User> list(HttpServletRequest request) {
        // 获取当前登录用户id
        System.out.println("当前用户ID: " + LoginInfoUtils.getLoginUserId(request));
        return userService.findAll();
    }

    @PostMapping("/save")
    public String save(@RequestBody LoginInfo loginInfo) {
        System.out.println(loginInfo);
        return "success";
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginInfo loginInfo) {
        Map<String, Object> result = new HashMap<>();
        String token = userService.login(loginInfo);
        if (token == null) {
            result.put("status", false);
        } else {
            result.put("status", true);
            result.put("token", token);
        }
        return result;
    }

}
