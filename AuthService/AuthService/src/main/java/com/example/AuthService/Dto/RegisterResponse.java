package com.example.AuthService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private Long id;

    private String fullName;

    private String email;

   private String role;

    private String message;
}
