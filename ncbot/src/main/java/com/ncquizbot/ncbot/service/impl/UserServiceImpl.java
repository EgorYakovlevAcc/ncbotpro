package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.repo.UserRepository;
import com.ncquizbot.ncbot.service.QuestionService;
import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionService questionService;

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByChatId(Long chatId) {
        return userRepository.findUserByChatId(chatId);
    }

    @Override
    public User findUserByTelegramId(Integer telegramId) {
        return userRepository.findUserByTelegramId(telegramId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User createAndSaveUserByTelegramMessageIfCurrentDoesNotExist(Message message) {
        org.telegram.telegrambots.api.objects.User telegramUser = message.getFrom();
        User u = userRepository.findUserByTelegramId(telegramUser.getId());
        if (Objects.isNull(u)) {
            User user = new User();
            user.setActiveNow(false);
            user.setTelegramId(telegramUser.getId());
            user.setFirstName(telegramUser.getFirstName());
            user.setLastName(telegramUser.getLastName());
            Chat chat = message.getChat();
            user.setChatId(chat.getId());
            user.setCurrentQuestionId(-1);
            user.setUsername(telegramUser.getUserName());
            user.setQuestionNumber(0);
            user.setGameOver(false);
            user.setScore(0);
            userRepository.save(user);
            return user;
        } else {
            return u;

        }
    }

    public Question getNextQuestionForUser(User user) {
        return questionService.getNextQuestionByWeight(user.getQuestionNumber());
    }

    @Override
    public void updateLastUserSessionDate(User user) {
        user.setLastSessionDate(new Date());
        userRepository.save(user);
    }

    @Override
    public void increaseUserScore(User user, Integer questionWeight) {
        Integer currentScore = user.getScore();
        user.setScore(currentScore + questionWeight);
        userRepository.save(user);
    }

    @Override
    public Question setNextQuestionToUser(User user) {
        Question question = getNextQuestionForUser(user);
        if (Objects.nonNull(question)) {
            user.setCurrentQuestionId(question.getId());
            user.setQuestionNumber(question.getWeight());
            userRepository.save(user);
        }
        return question;
    }

    @Override
    public void turnOffUserActivityStatus(User user) {
        user.setActiveNow(false);
        userRepository.save(user);
    }

    public void updateUserSessionEndDate(User user) {
        user.setEndSessionDate(new Date());
        userRepository.save(user);
    }

    @Override
    public void setActiveStatusTrue(User user) {
        user.setActiveNow(true);
        userRepository.save(user);
    }

    @Override
    public void setGameOverForUser(User user) {
        user.setGameOver(true);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersByScoreAndIsPresentGiven(Integer score, boolean isPresentGiven) {
        return userRepository.findUsersByScoreAndIsPresentGiven(score, isPresentGiven);
    }

    @Override
    public List<User> findUsersByIsPresentGiven(boolean isPresentGiven) {
        return userRepository.findUsersByIsPresentGiven(isPresentGiven);
    }

    @Override
    public void givePresentToUser(User user) {
        user.setPresentGiven(true);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersByScoreBetweenAndIsPresentGiven(Integer startScore, Integer endScore, boolean isPresentGiven) {
        List<User> users = userRepository.findUsersByIsPresentGiven(true);
        return users.stream()
                .filter(user -> (user.getScore() > startScore) && (user.getScore() < endScore))
                .collect(Collectors.toList());
    }
}
