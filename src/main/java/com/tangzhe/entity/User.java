package com.tangzhe.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 唐哲
 * 2018-06-24 18:14
 */
@Entity(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String username;

    @Column(nullable = true)
    private String password;

}
