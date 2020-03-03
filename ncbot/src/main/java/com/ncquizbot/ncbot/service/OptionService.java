package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;

import java.util.List;

public interface OptionService {
    Option findOptionById(Integer id);
    List<Option> findOptionByQuestion(Question question);
    void createOptionsByQuestionAndContents(Integer questionId, List<String> contents);
    Integer getCorrectIndexOfOptionByAnswer(String answerStr);
    void editOptionsByQuestionAndContents(Integer questionId, List<String> contents);
    com.ncquizbot.ncbot.pojo.Option convertOptionModelToOptionPojo(Option option);
}
