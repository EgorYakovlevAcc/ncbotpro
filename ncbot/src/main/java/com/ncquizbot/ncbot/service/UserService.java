package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.User;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

public interface UserService {
    User findUserById(Integer id);
    User findUserByChatId(Long chatId);
    User findUserByTelegramId(Integer telegramId);
    List<User> findAll();
    void save(User user);
    void delete(User user);
    User createAndSaveUserByTelegramMessageIfCurrentDoesNotExist(Message message);
    void increaseUserScore(User user, Integer questionWeight);
    Question setNextQuestionToUser(User user);
    void turnOffUserActivityStatus(User user);
    Question getNextQuestionForUser(User user);
    void updateLastUserSessionDate(User user);
    void updateUserSessionEndDate(User user);
    void setActiveStatusTrue(User user);
    void setGameOverForUser(User user);
    List<User> findUsersByScoreAndIsPresentGiven(Integer score, boolean isPresentGiven);
    List<User> findUsersByIsPresentGiven(boolean isPresentGiven);
    void givePresentToUser(User user);
    List<User> findUsersByScoreBetweenAndIsPresentGiven(Integer startScore, Integer endScore, boolean isPresentGiven);
}
