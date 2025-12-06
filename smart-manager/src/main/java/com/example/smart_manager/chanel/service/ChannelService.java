package com.example.smart_manager.chanel.service;

import com.example.smart_manager.chanel.model.Channel;
import com.example.smart_manager.chanel.repository.ChannelRepository;
import com.example.smart_manager.security.TokenEncoder;
import com.example.smart_manager.web.dto.ChannelTargetRequest;
import com.example.smart_manager.web.dto.RegisterChannel;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class ChannelService {
    private static final String BOT_ALREADY_EXIST = "Bot with such name and type already exist!";
    private static final String ADDED_NEW_BOT = "New bot configuration have been registered!";

    private final ChannelRepository channelRepository;
    private final ChannelTargetService channelTargetService;

    public ChannelService(ChannelRepository channelRepository, ChannelTargetService channelTargetService) {
        this.channelRepository = channelRepository;
        this.channelTargetService = channelTargetService;
    }

    @Transactional
    public void registerBot(RegisterChannel request) {
        Optional<Channel> optChannel = channelRepository.findByBotNameAndType(request.getBotName(), request.getChannelType());

        if (optChannel.isPresent()) {
            throw new RuntimeException(BOT_ALREADY_EXIST);
        }

        Channel channel = Channel.builder()
                .botName(request.getBotName())
                .type(request.getChannelType())
                .authToken(TokenEncoder.encode(request.getAuthToken()))
                .build();

        channelRepository.save(channel);
        log.info(ADDED_NEW_BOT);

        List<ChannelTargetRequest> channelTargets = getChannelTargets(request.getAuthToken());
        channelTargetService.saveChannelTargets(channel, channelTargets);
    }

    public abstract List<ChannelTargetRequest> getChannelTargets(String authToken);
}
