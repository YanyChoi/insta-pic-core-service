package com.instantpic.coreservice.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.instantpic.coreservice.dto.user.UserDto;
import com.instantpic.coreservice.dto.user.UserList;
import com.instantpic.coreservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AmazonS3Client amazonS3Client;
    private String S3Bucket = "instapic-media";

    @Autowired
    public UserController(UserService userService, AmazonS3Client amazonS3Client) {
        this.userService = userService;
        this.amazonS3Client = amazonS3Client;
    }

    @GetMapping
    public UserDto getUserInfoById(String id) {
        UserDto user = userService.getUserInfoService(id);
        return user;
    }

    @GetMapping("/login")
    public UserDto login(String id, String pw) {

        UserDto user = userService.loginService(id, pw);
        return user;
    }

    @GetMapping("/search")
    public UserList search(Optional<String> keyword) {
        UserList result = userService.searchService(keyword.isEmpty() ? "" : keyword.get());
        return result;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestPart UserDto userDraft, @RequestPart MultipartFile profilePic) throws IOException {

        String originalName = profilePic.getOriginalFilename() + "-user-" + userDraft.getUserId(); // 파일 이름
        long size = profilePic.getSize(); // 파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(profilePic.getContentType());
        objectMetaData.setContentLength(size);

        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, originalName, profilePic.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // 접근가능한 URL 가져오기
        userDraft.setProfilePic(imagePath);

        UserDto user = userService.signupService(userDraft);
        return user;
    }

    @PutMapping("/change-pw")
    public UserDto changePw(String id, String oldPw, String newPw) {

        UserDto user = userService.changePwService(id, oldPw, newPw);
        return user;
    }
}
