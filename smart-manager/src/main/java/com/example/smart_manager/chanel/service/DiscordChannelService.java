package com.example.smart_manager.chanel.service;

import com.example.smart_manager.chanel.model.ChannelBotType;
import com.example.smart_manager.chanel.repository.ChannelRepository;
import com.example.smart_manager.web.dto.ChannelRequest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DiscordChannelService extends ChannelService {
    private static final String NO_CHANNELS_FOUND = "Discord bot with token [%s] doest not have channels!";
    private static final String SUCCESSFULLY_DATA_EXTRACT = "The Data for channels for bot [%s] is successfully extract!";

    private static final String BASE_GUILD_TARGETS_URL = "https://discord.com/api/v10";
    private static final String GUILDS_URL = "/users/@me/guilds";
    private static final String CHANNELS_URL = "/guilds/%s/channels";

    @Autowired
    public DiscordChannelService(ChannelRepository channelTargetRepository) {
        super(channelTargetRepository);
    }

    @Override
    public List<ChannelRequest> getChannels(String authToken) {
        WebClient channelClient = WebClient.builder()
                .baseUrl(BASE_GUILD_TARGETS_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, authToken)
                .build();

        List<String> guildsId = getDiscordGuildIds(authToken);
        List<ChannelRequest> channelRequests = new ArrayList<>();

        for (String guildId : guildsId) {
            List<ChannelRequest> currentChannelsList = channelClient.get()
                    .uri(CHANNELS_URL.formatted(guildId))
                    .retrieve()
                    .bodyToFlux(JsonNode.class)
                    .filter(ch -> ch.get("type").asInt() == 0)
                    .map(ch -> new ChannelRequest(ch.get("id").asText(), ch.get("name").asText()))
                    .collectList()
                    .block();

            if (currentChannelsList.isEmpty()) {
                throw new RuntimeException(NO_CHANNELS_FOUND.formatted(authToken));
            }

            log.info(SUCCESSFULLY_DATA_EXTRACT.formatted(authToken));
            channelRequests.addAll(currentChannelsList);
        }

        return channelRequests;
    }

    @Override
    public boolean canServiced(ChannelBotType channelType) {
        return channelType.equals(ChannelBotType.DISCORD);
    }

    private List<String> getDiscordGuildIds(String authToken) {
        WebClient discordGuildClient = WebClient.builder()
                .baseUrl(BASE_GUILD_TARGETS_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bot " + authToken)
                .build();

        return discordGuildClient.get()
                .uri(GUILDS_URL)
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .map(guild -> guild.get("id").asText())
                .collectList()
                .block();
    }
}
