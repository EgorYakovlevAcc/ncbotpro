package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.ScoreRangesMessenger;
import com.ncquizbot.ncbot.pojo.ScoreRangesResultArrayPojo;
import com.ncquizbot.ncbot.pojo.ScoreRangesResultPojo;
import com.ncquizbot.ncbot.repo.ScoreRangesMessengerRepository;
import com.ncquizbot.ncbot.service.ScoreRangesMessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ScoreRangesMessengerServiceImpl implements ScoreRangesMessengerService {
    @Autowired
    private ScoreRangesMessengerRepository scoreRangesMessengerRepository;

    @Override
    public ScoreRangesMessenger findScoreRangesMessengerById(Integer id) {
        return scoreRangesMessengerRepository.findScoreRangesMessengerById(id);
    }

    @Override
    public List<ScoreRangesMessenger> findAll() {
        return scoreRangesMessengerRepository.findAll();
    }

    @Override
    public void save(ScoreRangesMessenger scoreRangesMessenger) {
        scoreRangesMessengerRepository.save(scoreRangesMessenger);
    }

    @Override
    public void delete(ScoreRangesMessenger scoreRangesMessenger) {
        scoreRangesMessengerRepository.delete(scoreRangesMessenger);
    }

    @Override
    public void createScoreRangeResultByPojo(List<ScoreRangesResultPojo> scoreRangesResultArrayPojo) throws IOException {
        for (int i = 0; i < scoreRangesResultArrayPojo.size(); i++) {
            ScoreRangesMessenger scoreRangesMessenger = new ScoreRangesMessenger();
            scoreRangesMessenger.setPicture(scoreRangesResultArrayPojo.get(i).getImageFile().getBytes());
            if (i == 0) {
                scoreRangesMessenger.setMinBorder(0);
            } else {
                scoreRangesMessenger.setMinBorder(scoreRangesResultArrayPojo.get(i - 1).getMaxRange());
            }
            scoreRangesMessenger.setMaxBorder(scoreRangesResultArrayPojo.get(i).getMaxRange());
            scoreRangesMessenger.setText(scoreRangesResultArrayPojo.get(i).getText());
            scoreRangesMessengerRepository.save(scoreRangesMessenger);
        }
    }
}
