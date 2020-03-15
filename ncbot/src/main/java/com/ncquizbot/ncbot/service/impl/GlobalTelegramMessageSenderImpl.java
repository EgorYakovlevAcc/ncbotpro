package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.service.GlobalTelegramMessageSender;
import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalTelegramMessageSenderImpl implements GlobalTelegramMessageSender {
    @Autowired
    private UserService userService;
    @Override
    public void sendGlobalMessage(String text) {

    }
}
