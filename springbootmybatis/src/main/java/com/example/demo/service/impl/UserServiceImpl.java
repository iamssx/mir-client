package com.example.demo.service.impl;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SSX on 2017/8/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public UserInfo findByUsername(String username) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        return userInfos == null ? null : userInfos.get(0);
    }

    public List<SysRole> getSysUserRoleListByUserInfo(UserInfo userInfo) {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
        criteria.andUidEqualTo(userInfo.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        List<Integer> list = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoles) {
            list.add(sysUserRole.getRid());
        }
        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria1 = sysRoleExample.createCriteria();
        criteria1.andIdIn(list);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        return sysRoles;
    }

    public List<SysPermission> getSysPermissionBySysRole(SysRole sysRole) {
        SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = sysRolePermissionExample.createCriteria();
        criteria.andRidEqualTo(sysRole.getId());
        List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectByExample(sysRolePermissionExample);
        ArrayList<Integer> list = new ArrayList<>();
        for (SysRolePermission permission : sysRolePermissions) {
            list.add(permission.getPid());
        }
        SysPermissionExample sysPermissionExample = new SysPermissionExample();
        SysPermissionExample.Criteria criteria1 = sysPermissionExample.createCriteria();
        criteria1.andIdIn(list);
        return sysPermissionMapper.selectByExample(sysPermissionExample);
    }
}
