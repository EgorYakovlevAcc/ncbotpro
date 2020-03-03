package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.Answer;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.pojo.QuestionAndOptions;
import com.ncquizbot.ncbot.pojo.QuestionOptionsAnswer;

import java.util.List;

public interface QuestionService {
    Question findQuestionById(Integer id);
    List<Question> findAll();
    void save(Question question);
    void delete(Question question);
    void createQuestionWithOptionsAndAnswer(QuestionOptionsAnswer questionOptionsAnswer);
    Question getNextQuestion(Integer currentQuestionId);
    Question findFirstQuestion();
    void deleteQuestionById(Integer id);
    QuestionOptionsAnswer convertQuestionToQuestionWithOptions(Question question);
    Integer getCorrectIndexOfOptionByAnswer(Question question, Answer answer);
    void editQuestionWithOptions(QuestionOptionsAnswer questionOptionsAnswer);
}
