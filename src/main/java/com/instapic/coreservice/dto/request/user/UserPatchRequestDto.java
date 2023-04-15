package com.instapic.coreservice.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter @Builder
public class UserPatchRequestDto {
    private Optional<String> userName;
    private Optional<String> fullName;
    private Optional<String> pw;
    private Optional<String> profilePictureUrl;
    private Optional<String> bio;
    private Optional<String> url;
}
