package com.tangzhe.controller;

import com.tangzhe.entity.User;
import com.tangzhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<User> list() {
        return userService.findAll();
    }

}
