package com.example.AuthService.Service;

import com.example.AuthService.Dto.*;

public interface AuthService {

    RegisterResponse  register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);
}
