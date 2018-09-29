package com.springboot.template.service.impl;

import com.springboot.template.entity.po.User;
import com.springboot.template.mapper.UserMapper;
import com.springboot.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinyu on 2018/9/28.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findUserByUUID(String uuid) {
        return userMapper.selectByUUID(uuid);
    }

    @Override
    public int selectByUser(User user) {
        return userMapper.selectByUser(user);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
