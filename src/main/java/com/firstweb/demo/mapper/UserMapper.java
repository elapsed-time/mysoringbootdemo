package com.firstweb.demo.mapper;

import com.firstweb.demo.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author zxx
 * @2019/7/17 15:54
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findbyToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findbyID(@Param("id") Integer creator);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name}, token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User dbuser);
}
