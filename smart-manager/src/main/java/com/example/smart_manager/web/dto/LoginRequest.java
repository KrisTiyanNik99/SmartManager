package com.example.smart_manager.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email is not correct!")
    private String email;

    @NotBlank()
    @Size(min = 5, max = 20, message = "Incorrect password!")
    private String password;
}
