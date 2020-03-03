package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.repo.OptionRepository;
import com.ncquizbot.ncbot.service.OptionService;
import com.ncquizbot.ncbot.service.QuestionService;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private QuestionService questionService;

    @Override
    public Option findOptionById(Integer id) {
        return optionRepository.findOptionById(id);
    }

    @Override
    public List<Option> findOptionByQuestion(Question question) {
        return optionRepository.findOptionByQuestion(question);
    }

    @Override
    public void createOptionsByQuestionAndContents(Integer questionId, List<String> contents) {
        Question question = questionService.findQuestionById(questionId);
        for (String content : contents) {
            Option option = new Option();
            option.setQuestion(question);
            option.setContent(content);
            optionRepository.save(option);
        }
    }

    @Override
    public Integer getCorrectIndexOfOptionByAnswer(String answerStr) {
        return null;
    }

    @Override
    public void editOptionsByQuestionAndContents(Integer questionId, List<String> contents) {
        Question question = questionService.findQuestionById(questionId);
        optionRepository.deleteOptionsByQuestion(question);
        //createOptionsByQuestionAndContents(questionId, contents);
    }

    @Override
    public com.ncquizbot.ncbot.pojo.Option convertOptionModelToOptionPojo(Option option) {
        com.ncquizbot.ncbot.pojo.Option optionPojo = new com.ncquizbot.ncbot.pojo.Option();
        optionPojo.setContent(option.getContent());
        return optionPojo;
    }

}
