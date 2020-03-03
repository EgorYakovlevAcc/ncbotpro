package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    Option findOptionById(Integer id);
    List<Option> findOptionByQuestion(Question question);
    void deleteOptionsByQuestion(Question question);
}
