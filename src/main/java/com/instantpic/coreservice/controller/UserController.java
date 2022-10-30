package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

}
