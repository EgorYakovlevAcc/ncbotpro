package com.ncquizbot.ncbot.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
@NoArgsConstructor
@Getter
@Setter
public class BotInitializer {
    private Bot bot;
    private TelegramBotsApi telegramBotsApi;

    @Autowired
    public BotInitializer(Bot bot, TelegramBotsApi telegramBotsApi) {
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}
