package com.springboot.template.service;

import com.springboot.template.entity.po.User;

/**
 * Created by jinyu on 2018/9/28.
 */
public interface UserService {
    int addUser(User user);
    User findUserByUUID(String uuid);
    int selectByUser(User user);
    User selectByPrimaryKey(Integer id);
}
