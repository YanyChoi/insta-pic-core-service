package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.user.UserDto;
import com.instantpic.coreservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto loginService(String userId, String pw) {
        UserDto result;
        result = userRepository.readUserByIdAndPw(userId, pw).get();
        return result;

    }

    public UserDto logoutService(String userId) {
        UserDto result;
        result = userRepository.readUserById(userId).get();
        return result;
    }

    public UserDto signupService(UserDto user) {
        UserDto result = user;
        return user;
    }

}
