package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/delete/{id}")
    public ResponseEntity getDeleteUser(@PathVariable("id") Integer id){
        User user = userService.findUserById(id);
        userService.delete(user);
        return ResponseEntity.ok(null);
    }
}
