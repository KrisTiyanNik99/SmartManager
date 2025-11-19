package com.example.smart_manager;

import com.example.smart_manager.chanel.repository.ChannelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner {
    private final ChannelRepository repository;

    public Test(ChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
//        ChannelConfiguration channelConfiguration = ChannelConfiguration.builder()
//                .authToken("ddZASDASKk1jsdfldsk")
//                .isActive(true)
//                .botName("My order bot")
//                .type(ChannelType.DISCORD)
//                .build();
//
//        ChannelConfiguration channelConfiguration1 = ChannelConfiguration.builder()
//                .authToken("ddZASDASKk1jswasfdsdfldsk")
//                .isActive(true)
//                .botName("My order bot")
//                .type(ChannelType.VIBER)
//                .build();
//
//        repository.save(channelConfiguration);
//        repository.save(channelConfiguration1);
    }
}
