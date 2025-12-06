package com.example.smart_manager.chanel.service;

import com.example.smart_manager.chanel.model.Channel;
import com.example.smart_manager.chanel.model.ChannelTarget;
import com.example.smart_manager.chanel.repository.ChannelTargetRepository;
import com.example.smart_manager.web.dto.ChannelTargetRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChannelTargetService {
    private static final String CHANNEL_TARGETS = "New channel targets are registered!";

    private final ChannelTargetRepository channelTargetRepository;

    @Autowired
    public ChannelTargetService(ChannelTargetRepository channelTargetRepository) {
        this.channelTargetRepository = channelTargetRepository;
    }

    public void saveChannelTargets(Channel channel, List<ChannelTargetRequest> channelTargetRequest) {
        List<ChannelTarget> channelTargetList = channelTargetRequest.stream()
                .map(target -> toChannelTarget(channel, target))
                .toList();

        channelTargetRepository.saveAll(channelTargetList);
        log.info(CHANNEL_TARGETS);
    }

    //  ################################### ------------------        Support methods        ------------------ ###################################
    private ChannelTarget toChannelTarget(Channel channel, ChannelTargetRequest channelTargetRequest) {
        return ChannelTarget.builder()
                .channel(channel)
                .externalId(channelTargetRequest.getExternalId())
                .name(channelTargetRequest.getName())
                .build();
    }
}
