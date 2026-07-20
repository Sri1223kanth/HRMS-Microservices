package com.example.AuthService.Service;

import com.example.AuthService.Dto.*;
import com.example.AuthService.Entity.RefreshToken;
import com.example.AuthService.Entity.User;
import com.example.AuthService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public  class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already Exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().name(),
                "User Registered Successfully"
        );
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        String accessToken = jwtService.generateToken(user);

        String refreshToken = refreshTokenService
                .createRefreshToken(user)
                .getToken();

        return new AuthenticationResponse(
                accessToken,
                refreshToken
        );
    }


    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenService
                .verifyRefreshToken(request.getRefreshToken());

        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return new AuthenticationResponse(
                accessToken,
                refreshToken.getToken()
        );
    }
}
