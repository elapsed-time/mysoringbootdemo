package com.firstweb.demo.service;

import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxx
 * @2019/7/24 14:31
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser=userMapper.findByAccountId(user.getAccountId());
        if(dbuser==null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setToken(user.getToken());
            dbuser.setName(user.getName());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            userMapper.update(dbuser);
        }
    }
}
