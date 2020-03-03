package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.service.AnswerService;
import com.ncquizbot.ncbot.service.OptionService;
import com.ncquizbot.ncbot.service.QuestionService;
import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> getShowUsers(Model model) {
       return userService.findAll();
    }

    @GetMapping(value = {"/index", "/"})
    public String getIndex(Model model) {
        System.out.println("getIndex");
        return "index";
    }


}
