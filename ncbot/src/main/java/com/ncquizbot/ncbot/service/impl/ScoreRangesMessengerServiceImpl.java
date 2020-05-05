package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.ScoreRangesMessenger;
import com.ncquizbot.ncbot.repo.ScoreRangesMessengerRepository;
import com.ncquizbot.ncbot.service.ScoreRangesMessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    public void createScoreRangeResultByPojo(Integer max, String text, byte[] imageByte) throws IOException {
        ScoreRangesMessenger scoreRangesMessenger = new ScoreRangesMessenger();
        if (Objects.nonNull(imageByte)) {
            scoreRangesMessenger.setPicture(imageByte);
        }
        scoreRangesMessenger.setMinBorder(getMinBorderForScoreRange(max));
        scoreRangesMessenger.setMaxBorder(max);
        scoreRangesMessenger.setText(text);
        scoreRangesMessengerRepository.save(scoreRangesMessenger);
    }

    @Override
    public ScoreRangesMessenger findScoreRangesMessangerByScore(Integer score) {
        return scoreRangesMessengerRepository.findAll().stream()
                .filter(x -> score <= x.getMaxBorder() && score >= x.getMinBorder())
                .findFirst()
                .orElse(null);
    }

    private Integer getMinBorderForScoreRange(Integer currentMax) {
        List<ScoreRangesMessenger> scoreRangesMessengerList = scoreRangesMessengerRepository.findAll();
        return scoreRangesMessengerList.stream()
                .map(ScoreRangesMessenger::getMaxBorder)
                .filter(x -> x < currentMax)
                .max(Comparator.comparing(x -> x + 1))
                .orElse(0) + 1;
    }
}
