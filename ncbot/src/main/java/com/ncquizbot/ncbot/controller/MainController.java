package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.pojo.BotState;
import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin()
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private Bot bot;

    @GetMapping("all/users")
    @ResponseBody
    public List<User> getShowUsers(Model model) {
        return userService.findAll();
    }

    @GetMapping(value = {"/", "/index"})
    public String getIndex() {
        return "forward:/index.html";
    }

}
