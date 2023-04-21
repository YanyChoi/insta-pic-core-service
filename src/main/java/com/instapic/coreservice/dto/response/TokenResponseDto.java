package com.instapic.coreservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class TokenResponseDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}
