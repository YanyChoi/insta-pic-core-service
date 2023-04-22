package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.domain.UserInfo;
import com.instapic.coreservice.domain.UserRole;
import com.instapic.coreservice.dto.response.TokenResponseDto;
import com.instapic.coreservice.dto.request.user.UserPatchRequestDto;
import com.instapic.coreservice.dto.request.user.UserPostRequestDto;
import com.instapic.coreservice.dto.response.user.UserDetailResponseDto;
import com.instapic.coreservice.jwt.JwtTokenProvider;
import com.instapic.coreservice.repository.amazon.AmazonS3Repository;
import com.instapic.coreservice.repository.follow.FollowRepository;
import com.instapic.coreservice.repository.media.FileType;
import com.instapic.coreservice.repository.user.UserInfoRepository;
import com.instapic.coreservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("aws.yaml")
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final FollowRepository followRepository;
    private final AmazonS3Repository amazonS3Repository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${MEDIA_BUCKET_URL}")
    private String mediaBucketUrl;

    @Transactional
    public TokenResponseDto login(String username, String password) {
        System.out.println("start login");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }

    public UserDetailResponseDto getUserDetails(Long userId) throws NoSuchElementException {
        UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        int followingCount = followRepository.countByUser(userInfo);
        int followedByCount = followRepository.countByTarget(userInfo);
        return UserDetailResponseDto.builder()
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserName())
                .fullName(userInfo.getFullName())
                .profilePictureUrl(userInfo.getProfilePictureUrl())
                .url(userInfo.getUrl())
                .bio(userInfo.getBio())
                .followingCount(followingCount)
                .followedByCount(followedByCount)
                .createdAt(userInfo.getCreatedAt().toString())
                .updatedAt(userInfo.getUpdatedAt().toString())
                .build();
    }

    public Long createUser(UserPostRequestDto dto, MultipartFile profilePicture) throws IOException {
        UserInfo userInfo = UserInfo.builder()
                .userName(dto.getUserName())
                .fullName(dto.getFullName())
                .bio(dto.getBio())
                .url(dto.getUrl())
                .build();
        uploadProfilePicture(userInfo, profilePicture);
        userInfoRepository.save(userInfo);
        User user = User.builder()
                .username(dto.getUserName())
                .password(passwordEncoder.encode(dto.getPw()))
                .userRole(UserRole.MEMBER)
                .build();
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public void updateUser(Long userId, UserPatchRequestDto dto, Optional<MultipartFile> profilePicture) throws IOException {
        UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        if (dto.getBio().isPresent()) {
            userInfo.setBio(dto.getBio().get());
        }
        if (dto.getUserName().isPresent()) {
            userInfo.setUserName(dto.getUserName().get());
        }
        if (dto.getFullName().isPresent()) {
            userInfo.setFullName(dto.getFullName().get());
        }
        if (dto.getUrl().isPresent()) {
            userInfo.setUrl(dto.getUrl().get());
        }
        if (profilePicture.isPresent()) {
            uploadProfilePicture(userInfo, profilePicture.get());
        }

    }

    private void uploadProfilePicture(UserInfo userInfo, MultipartFile newProfilePicture) throws IOException {
        if (userInfo.getProfilePictureUrl() != null) {
            amazonS3Repository.deleteObject(FileType.PROFILE_PICTURE, userInfo.getProfilePictureUrl().replace(mediaBucketUrl, ""));
        }
        String newProfilePictureUrl = mediaBucketUrl + "/" + FileType.PROFILE_PICTURE + "/" + amazonS3Repository.uploadObject(newProfilePicture, FileType.PROFILE_PICTURE);
        userInfo.setProfilePictureUrl(newProfilePictureUrl);
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    }

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
