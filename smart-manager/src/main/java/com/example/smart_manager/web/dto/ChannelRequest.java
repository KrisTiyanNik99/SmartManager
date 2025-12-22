package com.example.smart_manager.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelRequest {
    @NotBlank(message = "External id cannot be null or empty!")
    private String externalId;

    private String name;
}
