package com.instapic.coreservice.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.instapic.coreservice.dto.request.user.UserLoginRequestDto;
import com.instapic.coreservice.dto.request.user.UserPatchRequestDto;
import com.instapic.coreservice.dto.request.user.UserPostRequestDto;
import com.instapic.coreservice.dto.response.TokenResponseDto;
import com.instapic.coreservice.dto.response.user.UserDetailResponseDto;
import com.instapic.coreservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AmazonS3Client amazonS3Client;
    private final String S3Bucket = "instapic-media";

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDetailResponseDto> getUserInfoById(@PathVariable Long userId) {
//        UserDto user = userService.getUserInfoService(id);
        return ResponseEntity.ok().body(userService.getUserDetails(userId));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestPart UserPostRequestDto body, @RequestPart MultipartFile profilePicture) throws IOException {
        Long userId = userService.createUser(body, profilePicture);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/user/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestPart UserPatchRequestDto body, @RequestPart(required = false) Optional<MultipartFile> profilePicture) throws IOException {
        userService.updateUser(userId, body, profilePicture);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody UserLoginRequestDto dto) {
        return userService.login(
                dto.getUsername(),
                dto.getPassword()
        );
    }

//    @GetMapping("/login")
//    public UserDto login(String id, String pw) {
//
//        UserDto user = userService.loginService(id, pw);
//        return user;
//    }
//
//    @GetMapping("/search")
//    public UserList search(Optional<String> keyword) {
//        UserList result = userService.searchService(keyword.isEmpty() ? "" : keyword.get());
//        return result;
//    }
//
//    @PostMapping("/signup")
//    public void signUp(@RequestPart UserPostRequestDto userDraft, @RequestPart MultipartFile profilePic) throws IOException {
//
//        String originalName = profilePic.getOriginalFilename() + "-user-" + userDraft.getUserName(); // 파일 이름
//
//        ObjectMetadata objectMetaData = new ObjectMetadata();
//        objectMetaData.setContentType(profilePic.getContentType());
//        objectMetaData.setContentLength(profilePic.getSize());
//
//        // S3에 업로드
//        amazonS3Client.putObject(
//                new PutObjectRequest(S3Bucket, originalName, profilePic.getInputStream(), objectMetaData)
//                        .withCannedAcl(CannedAccessControlList.PublicRead)
//        );
//
//        String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // 접근가능한 URL 가져오기
//
//        UserDto user = userService.signupService(userDraft);
//        return user;
//    }

//    @PutMapping("/change-pw")
//    public UserDto changePw(String id, String oldPw, String newPw) {
//
//        UserDto user = userService.changePwService(id, oldPw, newPw);
//        return user;
//    }
}
