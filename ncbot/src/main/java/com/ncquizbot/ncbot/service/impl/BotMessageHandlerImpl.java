package com.ncquizbot.ncbot.service.impl;

import com.google.inject.internal.cglib.core.$ClassNameReader;
import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.model.Answer;
import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.service.AnswerService;
import com.ncquizbot.ncbot.service.BotMessageHandler;
import com.ncquizbot.ncbot.service.QuestionService;
import com.ncquizbot.ncbot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BotMessageHandlerImpl implements BotMessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @Override
    public SendMessage handleMessage(Update update) {
        Message message = update.getMessage();
        return handleInputMessage(message);

    }

    private SendMessage handleInputMessage(Message message) {
        if (Objects.nonNull(message) && message.hasText()) {
            ReplyKeyboardMarkup replyKeyboardMarkup = null;
            String currentMessageText = message.getText();
            String ouputMessageText = "";
            User user = userService.createAndSaveUserByTelegramMessageIfCurrentDoesNotExist(message);
            userService.updateLastUserSessionDate(user);
            if (user.getCurrentQuestionId() != -1) {
                updateUserScore(user, currentMessageText);
            }
            Question nextQuestion = getQuestionForUser(user);
            if (Objects.isNull(nextQuestion)) {
                ouputMessageText = getGoodByeMessage(user);
            } else {
                if (nextQuestion.getOptions().size() > 1) {
                    replyKeyboardMarkup = getQuestionWithMultipleOptions(nextQuestion.getOptions());
                }
                ouputMessageText = nextQuestion.getContent();
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(ouputMessageText)
                    .setChatId(message.getChatId());
            if (Objects.nonNull(replyKeyboardMarkup)) {
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
            }
            return sendMessage;
        }
        return null;
    }

    private void updateUserScore(User user, String userAnswerText) {
        Question lastQuestion = questionService.findQuestionById(user.getCurrentQuestionId());
        Answer answer = lastQuestion.getAnswer();
        String answerText = Objects.isNull(answer) ? "" : answer.getContent();
        if (checkAnswer(userAnswerText, answerText)) {
            userService.increaseUserScore(user);
        }
    }

    private String getGoodByeMessage(User user) {
        userService.turnOffUserActivityStatus(user);
        userService.updateUserSessionEndDate(user);
        userService.delete(user);
        return "Thank you it was last question. Your score is " + user.getScore();
    }

    private Question getQuestionForUser(User user) {
        Question currentQuestion = null;
        if (user.getCurrentQuestionId() == -1) {
            currentQuestion = getFirstQuestionForUser(user);
        }
        if (!userService.checkIsThisQuestionLast(user)) {
            userService.setNextQuestionToUser(user);
            currentQuestion = questionService.findQuestionById(user.getCurrentQuestionId());
        }
        return currentQuestion;
    }

    private Question getFirstQuestionForUser(User user) {
        return questionService.findQuestionById(questionService.findFirstQuestion().getId());
    }

    private ReplyKeyboardMarkup getQuestionWithMultipleOptions(List<Option> options) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        for (Option option : options) {
            keyboardRow.add(option.getContent());
        }
        keyboardRowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    private boolean checkAnswer(String userAnswer, String answer) {
        userAnswer = userAnswer.toLowerCase();
        answer = answer.toLowerCase();
        if (userAnswer.equals(answer)) {
            return true;
        }
        return false;
    }
}
