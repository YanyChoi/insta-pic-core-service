package com.instapic.coreservice.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.instapic.coreservice.dto.request.user.UserLoginRequestDto;
import com.instapic.coreservice.dto.request.user.UserPostRequestDto;
import com.instapic.coreservice.dto.response.TokenResponseDto;
import com.instapic.coreservice.dto.response.user.UserDetailResponseDto;
import com.instapic.coreservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final AmazonS3Client amazonS3Client;
    private final String S3Bucket = "instapic-media";

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestPart UserPostRequestDto body, @RequestPart MultipartFile profilePicture) throws IOException {
        Long id = userService.createUser(body, profilePicture);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody UserLoginRequestDto dto) {
        TokenResponseDto token = userService.login(
                dto.getUsername(),
                dto.getPassword()
        );
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDetailResponseDto> getUserInfoById(@PathVariable Long userId) {
//        UserDto user = userService.getUserInfoService(id);
        return ResponseEntity.ok().body(userService.getUserDetailsById(userId));
    }
    @GetMapping("/user/me")
    public ResponseEntity<UserDetailResponseDto> getUserInfoByToken(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userService.getUserDetailByUsername(user.getUsername()));
    }


//    @PatchMapping("/user/{userId}")
//    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestPart UserPatchRequestDto body, @RequestPart(required = false) Optional<MultipartFile> profilePicture) throws IOException {
//        userService.updateUser(userId, body, profilePicture);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
