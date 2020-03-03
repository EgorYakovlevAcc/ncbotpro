package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.Answer;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.repo.AnswerRepository;
import com.ncquizbot.ncbot.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer findAnswerById(Integer id) {
        return answerRepository.findAnswerById(id);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void createAnswersByContents(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answerRepository.save(answer);
    }
}
