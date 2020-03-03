package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.QuestionAndUserAnswerNote;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.repo.QuestionAndUserAnswerNoteRepository;
import com.ncquizbot.ncbot.service.QuestionAndUserAnswerNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAndUserAnswerNoteServiceImpl implements QuestionAndUserAnswerNoteService {
    @Autowired
    private QuestionAndUserAnswerNoteRepository quanRepository;
    @Override
    public QuestionAndUserAnswerNote findQuestionAndUserAnswerNoteById(Integer id) {
        return quanRepository.findQuestionAndUserAnswerNoteById(id);
    }

    @Override
    public QuestionAndUserAnswerNote findQuestionAndUserAnswerNoteByQuestionAndUser(Question question, User user) {
        return quanRepository.findQuestionAndUserAnswerNoteByQuestionAndUser(question, user);
    }

    @Override
    public List<QuestionAndUserAnswerNote> findQuestionAndUserAnswerNotesByUser(User user) {
        return quanRepository.findQuestionAndUserAnswerNotesByUser(user);
    }
}
