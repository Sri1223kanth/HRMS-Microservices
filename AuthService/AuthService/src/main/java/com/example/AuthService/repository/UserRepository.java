package com.example.AuthService.repository;

import com.example.AuthService.Entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    boolean existsByEmail(@Email(message ="Invalid Email") @NotBlank(message = "Email is Required") String email);

    Optional<User> findByEmail(String email);
}
