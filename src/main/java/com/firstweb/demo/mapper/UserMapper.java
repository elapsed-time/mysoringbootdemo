package com.firstweb.demo.mapper;

import com.firstweb.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zxx
 * @2019/7/17 15:54
 */
@Mapper
public interface UserMapper {
    @Insert("insert into spring.user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
