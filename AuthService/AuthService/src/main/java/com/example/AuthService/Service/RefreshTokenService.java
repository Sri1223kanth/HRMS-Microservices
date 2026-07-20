package com.example.AuthService.Service;

import com.example.AuthService.Entity.RefreshToken;
import com.example.AuthService.Entity.User;
import com.example.AuthService.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    // 7 days
    private final long refreshTokenDuration = 7 * 24 * 60 * 60;

    public RefreshToken createRefreshToken(User user) {

        refreshTokenRepository.deleteByUserId(user.getId());

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDate.from(LocalDateTime.now().plusSeconds(refreshTokenDuration)))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

        if (refreshToken.getExpiryDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh Token expired");
        }

        return refreshToken;
    }

    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());
    }
}
