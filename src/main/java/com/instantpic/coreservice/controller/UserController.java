package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.user.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/login")
    public UserDto login(String id, String pw) {

        return new UserDto(id, pw, "", "", "", "");
    }

    @GetMapping("/logout")
    public Boolean logout(String JWT) {
        return true;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody UserDto userDraft) {


        return userDraft;
    }
}
