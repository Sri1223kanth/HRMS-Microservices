package com.example.AuthService.Dto;

import com.example.AuthService.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegisterRequest {

    @NotBlank(message = "Full Name is Required")
    private String fullName;

    @Email(message ="Invalid Email")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    private String password;

    @NotNull(message = "Role is Required")
    private Role role;


}
