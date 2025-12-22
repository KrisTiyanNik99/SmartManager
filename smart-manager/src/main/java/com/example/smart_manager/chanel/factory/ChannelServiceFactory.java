package com.example.smart_manager.chanel.factory;

import com.example.smart_manager.chanel.model.ChannelBotType;
import com.example.smart_manager.chanel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelServiceFactory {
    private static final String NO_RIGHT_SERVICE = "Not correct service is found!";

    private final List<ChannelService> channelServices;

    @Autowired
    public ChannelServiceFactory(List<ChannelService> channelServices) {
        this.channelServices = channelServices;
    }

    public ChannelService getChannelTargetService(ChannelBotType channelType) {
        for (ChannelService channelService : channelServices) {
            if (channelService.canServiced(channelType)) {
                return channelService;
            }
        }

        throw new RuntimeException(NO_RIGHT_SERVICE);
    }
}
