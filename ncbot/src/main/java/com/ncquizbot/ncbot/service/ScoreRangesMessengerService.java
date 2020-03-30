package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.ScoreRangesMessenger;

import java.util.List;

public interface ScoreRangesMessengerService {
    ScoreRangesMessenger findScoreRangesMessengerById(Integer id);
    List<ScoreRangesMessenger> findAll();
    void save(ScoreRangesMessenger scoreRangesMessenger);
    void delete(ScoreRangesMessenger scoreRangesMessenger);
}
