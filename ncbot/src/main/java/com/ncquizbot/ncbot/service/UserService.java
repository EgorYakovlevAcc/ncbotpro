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
    boolean checkIsThisUserExists(User user);
    void increaseUserScore(User user, Integer questionWeight);
    Question setNextQuestionToUser(User user);
    void turnOffUserActivityStatus(User user);
    void setCurrentQuestionToUser(User user, Question question);
    Question getNextQuestionForUser(User user);
    void updateLastUserSessionDate(User user);
    void updateUserSessionEndDate(User user);
    void increaseUserQuestionNumber(User user);

    void setQuestionToUser(User user, Question nextQuestion);

    void setActiveStatusTrue(User user);

    void setGameOverForUser(User user);

    List<User> findUsersByScoreAndIsPresentGiven(Integer score, boolean isPresentGiven);
    List<User> findUsersByIsPresentGiven(boolean isPresentGiven);
}
