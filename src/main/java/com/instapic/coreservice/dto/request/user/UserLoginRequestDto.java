package com.instapic.coreservice.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserLoginRequestDto {
    private String username;
    private String password;
}
