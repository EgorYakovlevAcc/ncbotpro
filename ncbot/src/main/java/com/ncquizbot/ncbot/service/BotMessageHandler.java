package com.ncquizbot.ncbot.service;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface BotMessageHandler {
    SendMessage handleMessage(Update update);
}
