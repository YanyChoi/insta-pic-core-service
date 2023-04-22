package com.instapic.coreservice.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class UserPatchRequestDto {
    private Optional<String> userName;
    private Optional<String> fullName;
    private Optional<String> pw;
    private Optional<String> profilePictureUrl;
    private Optional<String> bio;
    private Optional<String> url;

    @Builder
    public UserPatchRequestDto(Optional<String> userName, Optional<String> fullName, Optional<String> pw, Optional<String> profilePictureUrl, Optional<String> bio, Optional<String> url) {
        this.userName = userName;
        this.fullName = fullName;
        this.pw = pw;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.url = url;
    }
}
