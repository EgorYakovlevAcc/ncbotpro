package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.service.GlobalTelegramMessageSender;
import com.ncquizbot.ncbot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

@Service
public class GlobalTelegramMessageSenderImpl implements GlobalTelegramMessageSender {
    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalTelegramMessageSenderImpl.class);
    @Autowired
    private UserService userService;
    @Autowired
    private Bot bot;

    @Async
    @Override
    public void sendGlobalMessage(String text) {
//        List<User> users = userService.findUsersByPresentGiven(true);
//        for (User u: users) {
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(u.getChatId())
//                    .setText(text);
//            try {
//                bot.execute(sendMessage);
//            } catch (TelegramApiException e) {
//                LOGGER.info("ERROR: during global sending messages to users!");
//            }
//        }
    }
}
