package com.ncquizbot.ncbot.service;

import com.ncquizbot.ncbot.bot.MessagesPackage;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface BotMessageHandler {
    MessagesPackage handleMessage(Update update);
}
