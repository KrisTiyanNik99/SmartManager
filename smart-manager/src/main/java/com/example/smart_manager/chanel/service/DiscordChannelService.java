package com.example.smart_manager.chanel.service;

import com.example.smart_manager.chanel.repository.ChannelRepository;
import com.example.smart_manager.web.dto.ChannelTargetRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DiscordChannelService extends ChannelService {
    private static final String BASE_GUILD_TARGETS_URL = "https://discord.com/api/v10";
    private static final String GUILDS_URL = "/users/@me/guilds";
    private static final String CHANNELS_URL = "/%s/channels";

    @Autowired
    public DiscordChannelService(ChannelRepository channelRepository, ChannelTargetService channelTargetService) {
        super(channelRepository, channelTargetService);
    }

    @Override
    public List<ChannelTargetRequest> getChannelTargets(String authToken) {
        List<String> guildsId = getDiscordGuilds(authToken);

        return null;
    }

    private List<String> getDiscordGuilds(String authToken) {
        WebClient discordClient = WebClient.builder()
                .baseUrl(BASE_GUILD_TARGETS_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bot " + authToken)
                .build();

        return discordClient.get()
                .uri(GUILDS_URL)
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .map(guild -> guild.get("id").asText())
                .collectList()
                .block();
    }
}
