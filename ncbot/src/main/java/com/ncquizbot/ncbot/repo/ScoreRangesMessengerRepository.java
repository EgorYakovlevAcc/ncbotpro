package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.ScoreRangesMessenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRangesMessengerRepository extends JpaRepository<ScoreRangesMessenger, Integer> {
    ScoreRangesMessenger findScoreRangesMessengerById(Integer id);
}
