package com.springboot.template.controller;

import com.springboot.template.entity.po.User;
import com.springboot.template.service.UserService;
import com.springboot.template.util.GetValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Created by jinyu on 2018/9/28.
 */

@RestController
@RequestMapping(value="user")
@Api("UserController相关api")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @ApiOperation(value="根据uuid获取用户信息",notes = "查询数据库中某个用户的信息")
    @ApiImplicitParam(name="id",value="id",required=true,dataType="String",paramType = "path")
    @RequestMapping(value="/{id}" ,  method = RequestMethod.GET)
    public User getUser(@PathVariable Integer id){
        return userService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "添加用户信息")
    @ApiImplicitParam
    @RequestMapping(value="/" ,  method = RequestMethod.POST)
    public User addUser(@RequestBody Map<String,Object> reqMap){
        User user = new User();
        user.setUsername(GetValue.getString(reqMap.get("username")));
        int count = userService.selectByUser(user);

        if(count == 0){
            user.setAvatar(GetValue.getString(reqMap.get("avatar")));
            user.setBirthday(GetValue.getString(reqMap.get("birthday")));
            user.setEmail(GetValue.getString(reqMap.get("email")));
            user.setNickname(GetValue.getString(reqMap.get("nickname")));
            user.setPassword(GetValue.getString(reqMap.get("password")));
            user.setRole(GetValue.getString(reqMap.get("role")));
            user.setPassword(GetValue.getString(reqMap.get("password")));
            user.setSex(GetValue.getString(reqMap.get("sex")));
            user.setSignature(GetValue.getString(reqMap.get("signature")));
            user.setSpecialty(GetValue.getString(reqMap.get("specialty")));
            user.setStatus(GetValue.getString(reqMap.get("status")));
            user.setCreattime(getTimeStamp());
            user.setUpdatetime(getTimeStamp());
            user.setUuid(getUUID());
            int successCount = userService.addUser(user);
            if(successCount > 0){
                return user;
            }else{
                return null;
            }
        }
        return null;
    }


}
