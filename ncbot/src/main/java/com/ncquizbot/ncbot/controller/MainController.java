package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.pojo.BotState;
import com.ncquizbot.ncbot.pojo.MessageToUsers;
import com.ncquizbot.ncbot.service.GlobalTelegramMessageSender;
import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin()
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private Bot bot;
    @Autowired
    private GlobalTelegramMessageSender globalTelegramMessageSender;

    @GetMapping("all/users")
    @ResponseBody
    public List<User> getShowUsers(Model model) {
        return userService.findAll();
    }

    @GetMapping(value = {"/", "/index"})
    public String getIndex() {
        return "forward:/index.html";
    }

    @PostMapping(value = "/send/global/message")
    public ResponseEntity postSendMessage(@RequestBody MessageToUsers messageToUsers){
        globalTelegramMessageSender.sendGlobalMessage(messageToUsers.getText(), messageToUsers.getMinScore(), messageToUsers.getMaxScore());
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/qrcode/get/present")
    public ResponseEntity getQrCodeGeneration(@RequestParam("chatId") Long chatId){
        User user = userService.findUserByChatId(chatId);
        if (Objects.isNull(user)){
            return ResponseEntity.ok("NO SUCH USER IN SYSTEM!");
        }
        if (!user.isPresentGiven()) {
            if (user.isGameOver()) {
                userService.givePresentToUser(user);
                return ResponseEntity.ok("PRESENT IS GIVEN TO USER!");
            }
            else {
                return ResponseEntity.ok("USER DOES NOT FINISH HIS GAME!");
            }
        }
        return ResponseEntity.ok("PRESENT HAS BEEN GIVEN TO USER!");
    }
}
