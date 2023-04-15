package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.dto.request.user.UserPatchRequestDto;
import com.instapic.coreservice.dto.request.user.UserPostRequestDto;
import com.instapic.coreservice.dto.response.user.UserDetailResponseDto;
import com.instapic.coreservice.repository.AmazonS3Repository;
import com.instapic.coreservice.repository.FileType;
import com.instapic.coreservice.repository.UserRepository;
import com.instapic.coreservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@PropertySource("aws.yaml")
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final AmazonS3Repository amazonS3Repository;

    @Value("${MEDIA_BUCKET_URL}")
    private String mediaBucketUrl;

    public UserDetailResponseDto getUserDetails(Long userId) throws NoSuchElementException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        Long followingCount = followRepository.countByUser(user);
        Long followedByCount = followRepository.countByTarget(user);
        return UserDetailResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .profilePictureUrl(user.getProfilePictureUrl())
                .url(user.getUrl())
                .bio(user.getBio())
                .followingCount(followingCount)
                .followedByCount(followedByCount)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public Long createUser(UserPostRequestDto dto, MultipartFile profilePicture) throws IOException {
        User user = User.builder()
                .userName(dto.getUserName())
                .fullName(dto.getFullName())
                .pw(dto.getPw())
                .bio(dto.getBio())
                .url(dto.getUrl())
                .build();
        uploadProfilePicture(user, profilePicture);
        userRepository.save(user);
        return user.getUserId();
    }

    @Transactional
    public void updateUser(Long userId, UserPatchRequestDto dto, Optional<MultipartFile> profilePicture) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        if (dto.getBio().isPresent()) {
            user.setBio(dto.getBio().get());
        }
        if (dto.getUserName().isPresent()) {
            user.setUserName(dto.getUserName().get());
        }
        if (dto.getFullName().isPresent()) {
            user.setFullName(dto.getFullName().get());
        }
        if (dto.getUrl().isPresent()) {
            user.setUrl(dto.getUrl().get());
        }
        if (dto.getPw().isPresent()) {
            user.setPw(dto.getPw().get());
        }
        if (profilePicture.isPresent()) {
            uploadProfilePicture(user, profilePicture.get());
        }

    }

    private void uploadProfilePicture(User user, MultipartFile newProfilePicture) throws IOException {
        if (!user.getProfilePictureUrl().isEmpty()) {
            amazonS3Repository.deleteObject(FileType.PROFILE_PICTURE, user.getProfilePictureUrl().replace(mediaBucketUrl, ""));
        }
        String newProfilePictureUrl = mediaBucketUrl + "/" + FileType.PROFILE_PICTURE + "/" + amazonS3Repository.uploadObject(newProfilePicture, FileType.PROFILE_PICTURE);
        user.setProfilePictureUrl(newProfilePictureUrl);
    }

//
//    public UserDto getUserInfoService(String userId) {
//        UserDto result;
//        result = userRepository.readUserById(userId).get();
//        return result;
//    }
//    public UserDto loginService(String userId, String pw) {
//        UserDto result;
//        result = userRepository.readUserByIdAndPw(userId, pw).get();
//        return result;
//    }
//
//    public UserList searchService(String keyword) {
//        UserList result = new UserList();
//        result.setUsers(userRepository.readUserByKeyword(keyword));
//        result.setCount(result.getUsers().size());
//        return result;
//    }
//
//    public UserDto signupService(UserDto user) {
//        UserDto result;
//        if (userRepository.createUser(user)) {
//            result = userRepository.readUserByIdAndPw(user.getUserId(), user.getPw()).get();
//        }
//        else {
//            result = new UserDto();
//            result.setIntroduction("Signup Fail");
//        }
//        return result;
//    }
//
//    public UserDto changePwService(String id, String oldPw, String newPw)
//    {
//        UserDto user;
//
//        if (userRepository.readUserByIdAndPw(id, oldPw).isPresent()) {
//            user = userRepository.updateUserPw(id, newPw).get();
//        }
//        else {
//            user = new UserDto();
//        }
//
//        return user;
//    }

}
