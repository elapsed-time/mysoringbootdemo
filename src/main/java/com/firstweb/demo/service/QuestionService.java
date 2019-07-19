package com.firstweb.demo.service;

import com.firstweb.demo.mapper.QuestionMapper;
import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.Question;
import com.firstweb.demo.model.User;
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

    public List<QuestionPOJO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionPOJO> questionPOJOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findbyID(question.getCreator());
            QuestionPOJO questionPOJO = new QuestionPOJO();
            BeanUtils.copyProperties(question,questionPOJO);
            questionPOJO.setUser(user);
            questionPOJOList.add(questionPOJO);
        }
        return questionPOJOList;
    }
}
