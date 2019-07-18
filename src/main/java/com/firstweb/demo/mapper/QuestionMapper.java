package com.firstweb.demo.mapper;

import com.firstweb.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zxx
 * @2019/7/17 15:54
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into spring.question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
