package com.example.smart_manager.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3 to 20 symbols.")
    private String username;

    @NotBlank(message = "Email is not correct!")
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 20, message = "Password is too short or too long!")
    private String password;
}
