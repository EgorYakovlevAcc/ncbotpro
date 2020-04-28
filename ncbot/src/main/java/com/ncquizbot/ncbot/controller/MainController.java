package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.model.ScoreRangesMessenger;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.pojo.BotState;
import com.ncquizbot.ncbot.pojo.MessageToUsers;
import com.ncquizbot.ncbot.pojo.ScoreRangesResultArrayPojo;
import com.ncquizbot.ncbot.pojo.ScoreRangesResultPojo;
import com.ncquizbot.ncbot.service.GlobalTelegramMessageSender;
import com.ncquizbot.ncbot.service.ScoreRangesMessengerService;
import com.ncquizbot.ncbot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin()
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private GlobalTelegramMessageSender globalTelegramMessageSender;
    @Autowired
    private ScoreRangesMessengerService scoreRangesMessengerService;

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

    @GetMapping(value = "/score/ranges")
    public List<ScoreRangesMessenger> getAllScoreRangesMessenger(){
        return scoreRangesMessengerService.findAll();
    }

    @PostMapping(value = "/score/ranges")
    public ResponseEntity postScoreRangesMessenger(@RequestParam("max") String max, @RequestParam("text") String text,
                                                   @RequestParam(name = "image", required = false) MultipartFile image){
        LOGGER.info("postScoreRangesMessenger: max = {} text = {}", max, text);
        try {
            scoreRangesMessengerService.createScoreRangeResultByPojo(Integer.valueOf(max), text, Objects.nonNull(image)? image.getBytes(): null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
