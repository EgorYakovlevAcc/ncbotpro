package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.ScoreRangesMessenger;
import com.ncquizbot.ncbot.pojo.ScoreRangesResultArrayPojo;

import java.io.IOException;
import java.util.List;

public interface ScoreRangesMessengerService {
    ScoreRangesMessenger findScoreRangesMessengerById(Integer id);
    List<ScoreRangesMessenger> findAll();
    void save(ScoreRangesMessenger scoreRangesMessenger);
    void delete(ScoreRangesMessenger scoreRangesMessenger);

    void createScoreRangeResultByPojo(ScoreRangesResultArrayPojo scoreRangesResultArrayPojo) throws IOException;
}
