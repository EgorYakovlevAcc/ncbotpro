package com.ncquizbot.ncbot.service;

import org.telegram.telegrambots.exceptions.TelegramApiException;

public interface GlobalTelegramMessageSender {
    void sendGlobalMessage(String text);
}
