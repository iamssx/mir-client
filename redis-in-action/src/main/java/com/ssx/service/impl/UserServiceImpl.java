package com.ssx.service.impl;

import com.ssx.dao.UserMapper;
import com.ssx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SSX on 2017/11/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer findIdByUserName(String username) {
        return userMapper.findByUsername(username);
    }


}
