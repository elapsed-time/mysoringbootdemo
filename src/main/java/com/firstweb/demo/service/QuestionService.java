package com.firstweb.demo.service;

import com.firstweb.demo.mapper.QuestionMapper;
import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.Question;
import com.firstweb.demo.model.QuestionExample;
import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.PagePOJO;
import com.firstweb.demo.pojo.QuestionPOJO;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalPage;
        Integer totalcount = (int)questionMapper.countByExample(new QuestionExample());//查询总数据
        //计算总页数
        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pagePOJO.setPage(totalPage, page);
        Integer pagenum = size * (page - 1);//将页码转换为偏移数
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(pagenum, size));//查询分页
        List<QuestionPOJO> questionPOJOList = new ArrayList<>();

        for (Question question : questions) {//将问题数据存入list
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionPOJO questionPOJO = new QuestionPOJO();
            BeanUtils.copyProperties(question, questionPOJO);
            questionPOJO.setUser(user);
            questionPOJOList.add(questionPOJO);
        }
        pagePOJO.setQuestions(questionPOJOList);//将数据封装

        return pagePOJO;
    }

    public PagePOJO list(Integer userId, Integer page, Integer size) {
        PagePOJO pagePOJO = new PagePOJO();
        Integer totalPage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalcount = (int)questionMapper.countByExample(questionExample);//查询总数据
        //计算总页数
        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pagePOJO.setPage(totalPage, page);
        Integer pagenum = size * (page - 1);//将页码转换为偏移数
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(pagenum, size));//查询分页
        List<QuestionPOJO> questionPOJOList = new ArrayList<>();

        for (Question question : questions) {//将问题数据存入list
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionPOJO questionPOJO = new QuestionPOJO();
            BeanUtils.copyProperties(question, questionPOJO);
            questionPOJO.setUser(user);
            questionPOJOList.add(questionPOJO);
        }
        pagePOJO.setQuestions(questionPOJOList);//将数据封装

        return pagePOJO;
    }

    public QuestionPOJO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionPOJO questionPOJO = new QuestionPOJO();
        BeanUtils.copyProperties(question,questionPOJO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionPOJO.setUser(user);
        return questionPOJO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //更新

            Question updatequestion = new Question();
            updatequestion.setGmtModified(System.currentTimeMillis());
            updatequestion.setTitle(question.getTitle());
            updatequestion.setDescription(question.getDescription());
            updatequestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updatequestion, example);
        }
    }
}
