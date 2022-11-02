package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.user.UserDto;
import com.instantpic.coreservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public UserDto login(String id, String pw) {

        UserDto user = userService.loginService(id, pw);
        return user;
    }

    @GetMapping("/logout")
    public Boolean logout(String id, String pw) {
        return true;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody UserDto userDraft) {

        UserDto user = userService.signupService(userDraft);
        return user;
    }
}
