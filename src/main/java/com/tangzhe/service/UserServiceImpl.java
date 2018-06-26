package com.tangzhe.service;

import com.tangzhe.entity.LoginInfo;
import com.tangzhe.entity.User;
import com.tangzhe.repository.UserRepository;
import com.tangzhe.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 唐哲
 * 2018-06-24 18:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public String login(LoginInfo loginInfo) {
        User user = userRepository.findByUsernameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
        if (user == null) {
            return null;
        }
        //String token = JWTUtils.getInstance().getToken(user.getId() + "");
        // 设置token过期
        String token = JWTUtils.getInstance().getToken(String.valueOf(user.getId()), 10);
        return token;
    }

}
