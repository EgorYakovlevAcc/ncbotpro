package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.pojo.BotState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bot")
@CrossOrigin()
public class BotController {
    @Autowired
    private Bot bot;

    @PostMapping(value = {"/turn"})
    @ResponseBody
    public ResponseEntity postBotTurn(@RequestBody BotState botState) {
        bot.setIsBotActive(!botState.isActive());
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = {"/get/turn"})
    @ResponseBody
    public BotState getBotTurn() {
        BotState botState = new BotState();
        botState.setActive(bot.getIsBotActive());
        return botState;
    }
}
