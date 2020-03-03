package com.ncquizbot.ncbot.bot;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.service.BotMessageHandler;
import com.ncquizbot.ncbot.service.QuestionService;
import com.ncquizbot.ncbot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    @Autowired
    private BotMessageHandler botMessageHandler;

    @Override
    public void onUpdateReceived(Update update) {
        LOGGER.info("UPDATE = {}", update);
        SendMessage answer = botMessageHandler.handleMessage(update);
        try {
            execute(answer); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "netcracker_quiz_bot";
    }

    @Override
    public String getBotToken() {
        return "827026140:AAECQOwWUsWYkygsr89VNo0DeHWhWr_Lml4";
    }
}