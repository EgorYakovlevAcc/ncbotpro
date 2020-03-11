package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;

import java.util.List;

public interface OptionService {
    Option findOptionById(Integer id);
    List<Option> findOptionByQuestion(Question question);
    void createOptionsByQuestionAndContents(Integer questionId, List<String> contents);
    Integer getCorrectIndexOfOptionByAnswer(String answerStr);
    void editOptionsByQuestionAndContents(Integer questionId, List<com.ncquizbot.ncbot.model.Option> previousOptions, List<Option> options);
    void delete(Option option);
    com.ncquizbot.ncbot.pojo.Option convertOptionModelToOptionPojo(Option option);
}
