package com.instapic.coreservice.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserPostRequestDto {
    private String userName;
    private String fullName;
    private String pw;
    private String profilePictureUrl;
    private String bio;
    private String url;
}
