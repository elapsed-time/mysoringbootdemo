package com.firstweb.demo.service;

import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.User;
import com.firstweb.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxx
 * @2019/7/24 14:31
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            User dbuser = users.get(0);
            User updateuser=new User();
            updateuser.setGmtModified(System.currentTimeMillis());
            updateuser.setToken(user.getToken());
            updateuser.setName(user.getName());
            updateuser.setAvatarUrl(user.getAvatarUrl());
            updateuser.setBio(user.getBio());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateuser, example);
        }
    }
}
