package com.instapic.coreservice.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserPostRequestDto {
    private String userName;
    private String fullName;
    private String pw;
    private String bio;
    private String url;

    @Builder
    public UserPostRequestDto(String userName, String fullName, String pw, String bio, String url) {
        this.userName = userName;
        this.fullName = fullName;
        this.pw = pw;
        this.bio = bio;
        this.url = url;
    }
}
