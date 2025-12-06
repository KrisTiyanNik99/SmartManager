package com.example.smart_manager.web.dto;

import com.example.smart_manager.chanel.model.ChannelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterChannel {
    @NotBlank(message = "Type cannot be null!")
    private ChannelType channelType;

    @NotBlank(message = "Fill name for the bot!")
    @Size(min = 3, max = 26)
    private String botName;

    @NotBlank(message = "Set bot auth token!")
    private String authToken;
}
