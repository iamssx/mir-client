package com.example.demo.service;

import com.example.demo.entity.UserInfo;

/**
 * Created by SSX on 2017/8/25.
 */
public interface UserService {

    UserInfo findByUsername(String username);
}
