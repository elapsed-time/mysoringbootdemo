package com.firstweb.demo.mapper;

import com.firstweb.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zxx
 * @2019/7/17 15:54
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into spring.question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from spring.question limit #{pagenum},#{size}")
    List<Question> list(@Param(value = "pagenum") Integer pagenum,@Param(value = "size") Integer size);

    @Select("select count(1) from spring.question")
    Integer count();

    @Select("select * from spring.question where creator=#{userId} limit #{pagenum},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "pagenum") Integer pagenum,@Param(value = "size") Integer size);
    @Select("select count(1) from spring.question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
}
