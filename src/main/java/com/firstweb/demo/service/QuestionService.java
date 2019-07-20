package com.firstweb.demo.service;

import com.firstweb.demo.mapper.QuestionMapper;
import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.Question;
import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.PagePOJO;
import com.firstweb.demo.pojo.QuestionPOJO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxx
 * @2019/7/19 10:43
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;//报错但不影响运行
    @Autowired
    private QuestionMapper questionMapper;

    public PagePOJO list(Integer page, Integer size) {
        PagePOJO pagePOJO = new PagePOJO();
        Integer totalcount = questionMapper.count();//查询总数据
        pagePOJO.setPage(totalcount,page,size);
        if (page<1){
            page=1;
        }
        if(page>pagePOJO.getTotalPage()){
            page=pagePOJO.getTotalPage();
        }
        Integer pagenum = size * (page - 1);//将页码转换为偏移数
        List<Question> questions = questionMapper.list(pagenum, size);//查询分页
        List<QuestionPOJO> questionPOJOList = new ArrayList<>();

        for (Question question : questions) {//将问题数据存入list
            User user = userMapper.findbyID(question.getCreator());
            QuestionPOJO questionPOJO = new QuestionPOJO();
            BeanUtils.copyProperties(question, questionPOJO);
            questionPOJO.setUser(user);
            questionPOJOList.add(questionPOJO);
        }
        pagePOJO.setQuestions(questionPOJOList);//将数据封装

        return pagePOJO;
    }
}
