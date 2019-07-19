package com.firstweb.demo.mapper;

import com.firstweb.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zxx
 * @2019/7/17 15:54
 */
@Mapper
public interface UserMapper {
    @Insert("insert into spring.user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from spring.user where token=#{token}")
    User findbyToken(@Param("token") String token);

    @Select("select * from spring.user where id=#{id}")
    User findbyID(@Param("id") Integer creator);
}
