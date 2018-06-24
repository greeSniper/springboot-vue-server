package com.tangzhe.service;

import com.tangzhe.entity.User;

import java.util.List;

/**
 * Created by 唐哲
 * 2018-06-24 18:17
 */
public interface UserService {

    void save(User user);

    User findById(Long id);

    List<User> findAll();

    void update(User user);

    void delete(Long id);

}
