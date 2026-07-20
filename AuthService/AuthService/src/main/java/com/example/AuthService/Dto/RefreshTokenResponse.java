package com.example.AuthService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
}
