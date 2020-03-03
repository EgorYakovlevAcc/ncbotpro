package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.QuestionAndUserAnswerNote;
import com.ncquizbot.ncbot.model.User;

import java.util.List;

public interface QuestionAndUserAnswerNoteService {
    QuestionAndUserAnswerNote findQuestionAndUserAnswerNoteById(Integer id);
    QuestionAndUserAnswerNote findQuestionAndUserAnswerNoteByQuestionAndUser(Question question, User user);
    List<QuestionAndUserAnswerNote> findQuestionAndUserAnswerNotesByUser(User user);
}
