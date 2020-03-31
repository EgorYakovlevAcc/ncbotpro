package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.ScoreRangesMessenger;

import java.io.IOException;
import java.util.List;

public interface ScoreRangesMessengerService {
    ScoreRangesMessenger findScoreRangesMessengerById(Integer id);
    List<ScoreRangesMessenger> findAll();
    void save(ScoreRangesMessenger scoreRangesMessenger);
    void delete(ScoreRangesMessenger scoreRangesMessenger);

    void createScoreRangeResultByPojo(Integer max, String text, byte[] imageByte) throws IOException;

    ScoreRangesMessenger findScoreRangesMessangerByScore(Integer score);
}
