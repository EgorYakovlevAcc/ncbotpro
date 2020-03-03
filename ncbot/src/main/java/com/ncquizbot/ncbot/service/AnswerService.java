package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.Answer;
import com.ncquizbot.ncbot.model.Question;

public interface AnswerService {
    Answer findAnswerById(Integer id);
    void save(Answer answer);
    void createAnswersByContents(Question question, String content);
}
