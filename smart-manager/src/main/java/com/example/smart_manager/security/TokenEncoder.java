package com.example.smart_manager.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenEncoder {
    private static final String EMPTY_TOKEN = "Token cannot be null";

    public static String encode(String token) {
        if (token == null) {
            throw new RuntimeException(EMPTY_TOKEN);
        }

        return Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }

    public static String decoded(String encodedToken) {
        if (encodedToken == null) {
            throw new RuntimeException(EMPTY_TOKEN);
        }

        byte[] decodedBytes = Base64.getDecoder().decode(encodedToken);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
