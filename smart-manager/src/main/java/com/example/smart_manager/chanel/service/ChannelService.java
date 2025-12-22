package com.example.smart_manager.chanel.service;

import com.example.smart_manager.chanel.model.ChannelBot;
import com.example.smart_manager.chanel.model.Channel;
import com.example.smart_manager.chanel.model.ChannelBotType;
import com.example.smart_manager.chanel.repository.ChannelRepository;
import com.example.smart_manager.security.TokenEncoder;
import com.example.smart_manager.web.dto.ChannelRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class ChannelService {
    private static final String CHANNEL_TARGETS = "New channel targets are registered!";
    private static final String WRONG_SERVICE_CLASS = "This is not the correct type of ChannelTargetService for this type Channel Bot!";

    private final ChannelRepository channelTargetRepository;

    public ChannelService(ChannelRepository channelTargetRepository) {
        this.channelTargetRepository = channelTargetRepository;
    }

    public void persistChannelsForBot(ChannelBot channelBot) {
        if (!canServiced(channelBot.getType())) {
            throw new RuntimeException(WRONG_SERVICE_CLASS);
        }

        List<ChannelRequest> channelRequest = getChannels(TokenEncoder.decoded(channelBot.getAuthToken()));
        List<Channel> channelList = channelRequest.stream()
                .map(target -> toChannelTarget(channelBot, target))
                .toList();

        channelTargetRepository.saveAll(channelList);
        log.info(CHANNEL_TARGETS);
    }

    public abstract boolean canServiced(ChannelBotType channelType);
    protected abstract List<ChannelRequest> getChannels(String authBotToken);


    //  ################################### ------------------        Support methods        ------------------ ###################################
    private Channel toChannelTarget(ChannelBot channelBot, ChannelRequest channelRequest) {
        return Channel.builder()
                .channelBot(channelBot)
                .externalId(channelRequest.getExternalId())
                .name(channelRequest.getName())
                .build();
    }
}
