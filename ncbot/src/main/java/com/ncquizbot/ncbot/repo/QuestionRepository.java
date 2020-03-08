package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findQuestionById(Integer id);
    List<Question> findQuestionsByWeight(Integer weight);
}
