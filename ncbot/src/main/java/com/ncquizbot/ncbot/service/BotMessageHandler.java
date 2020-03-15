package com.ncquizbot.ncbot.service;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface BotMessageHandler {
    PartialBotApiMethod handleMessage(Update update);
}
