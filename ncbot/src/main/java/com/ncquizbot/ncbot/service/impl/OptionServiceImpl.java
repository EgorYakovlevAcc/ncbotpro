package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.repo.OptionRepository;
import com.ncquizbot.ncbot.service.OptionService;
import com.ncquizbot.ncbot.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);
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
    @Transactional
    public void editOptionsByQuestionAndContents(Integer questionId, List<Option> previousOptions, List<com.ncquizbot.ncbot.pojo.Option> options) {
        for (com.ncquizbot.ncbot.model.Option option: previousOptions) {
            optionRepository.delete(option);
        }
        createOptionsByQuestionAndContents(questionId, options.stream()
                .map(x -> x.getContent())
                .collect(Collectors.toList()));
    }

    @Override
    public void delete(Option option) {
        optionRepository.delete(option);
    }

    @Override
    public com.ncquizbot.ncbot.pojo.Option convertOptionModelToOptionPojo(Option option) {
        com.ncquizbot.ncbot.pojo.Option optionPojo = new com.ncquizbot.ncbot.pojo.Option();
        optionPojo.setContent(option.getContent());
        return optionPojo;
    }

}
