package com.example.smart_manager.chanel.service;

import com.example.smart_manager.chanel.factory.ChannelServiceFactory;
import com.example.smart_manager.chanel.model.ChannelBot;
import com.example.smart_manager.chanel.repository.ChannelBotRepository;
import com.example.smart_manager.security.TokenEncoder;
import com.example.smart_manager.web.dto.RegisterBotRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ChannelBotService {
    private static final String BOT_ALREADY_EXIST = "Bot with such name and type already exist!";
    private static final String ADDED_NEW_BOT = "New bot configuration have been registered!";

    private final ChannelBotRepository channelBotRepository;
    private final ChannelServiceFactory channelServiceFactory;

    @Autowired
    public ChannelBotService(ChannelBotRepository channelRepository, ChannelServiceFactory channelServiceFactory) {
        this.channelBotRepository = channelRepository;
        this.channelServiceFactory = channelServiceFactory;
    }

    @Transactional
    public void registerBot(RegisterBotRequest request) {
        Optional<ChannelBot> optChannel = channelBotRepository.findByBotNameAndType(request.getBotName(), request.getChannelType());

        if (optChannel.isPresent()) {
            throw new RuntimeException(BOT_ALREADY_EXIST);
        }

        ChannelBot channelBot = ChannelBot.builder()
                .botName(request.getBotName())
                .type(request.getChannelType())
                .authToken(TokenEncoder.encode(request.getAuthToken()))
                .build();

        channelBotRepository.save(channelBot);
        log.info(ADDED_NEW_BOT);

        ChannelService channelTargetService = channelServiceFactory.getChannelTargetService(request.getChannelType());
        channelTargetService.persistChannelsForBot(channelBot);
    }
}
