package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.QuestionAndUserAnswerNote;
import com.ncquizbot.ncbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAndUserAnswerNoteRepository extends JpaRepository<QuestionAndUserAnswerNote, Integer> {
    QuestionAndUserAnswerNote findQuestionAndUserAnswerNoteById(Integer id);
    QuestionAndUserAnswerNote findQuestionAndUserAnswerNoteByQuestionAndUser(Question question, User user);
    List<QuestionAndUserAnswerNote> findQuestionAndUserAnswerNotesByUser(User user);
}
