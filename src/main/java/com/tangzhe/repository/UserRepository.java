package com.tangzhe.repository;

import com.tangzhe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 唐哲
 * 2018-06-24 18:17
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
