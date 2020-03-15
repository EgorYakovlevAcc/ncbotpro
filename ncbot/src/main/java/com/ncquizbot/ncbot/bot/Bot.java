package com.ncquizbot.ncbot.bot;

import com.ncquizbot.ncbot.service.BotMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    private boolean isBotActive = true;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    private final static String TURN_OFF_BOT_MESSAGE = "Sorry, I'm turned off. Please, try again later!";
    @Autowired
    private BotMessageHandler botMessageHandler;

    @Override
    public void onUpdateReceived(Update update) {
        LOGGER.info("UPDATE = {}", update);
        if (getIsBotActive()) {
            PartialBotApiMethod answer = botMessageHandler.handleMessage(update);
            try {
                if (answer instanceof SendMessage) {
                    SendMessage sendAnswer = (SendMessage) answer;
                    execute(sendAnswer); // Call method to send the message
                }
                else {
                    SendPhoto sendPhoto = (SendPhoto) answer;
                    sendPhoto(sendPhoto); // Call method to send the message
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            SendMessage answer = new SendMessage();
            answer.setChatId(update.getMessage().getChatId());
            answer.setText(TURN_OFF_BOT_MESSAGE);
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
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

    public boolean getIsBotActive() {
        return this.isBotActive;
    }

    public void setIsBotActive(boolean isBotActive) {
        this.isBotActive = isBotActive;
    }
}
