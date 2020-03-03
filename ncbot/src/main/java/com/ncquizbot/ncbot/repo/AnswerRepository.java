package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Answer findAnswerById(Integer id);
}
