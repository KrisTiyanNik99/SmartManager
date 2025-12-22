package com.example.smart_manager.chanel.repository;

import com.example.smart_manager.chanel.model.ChannelBot;
import com.example.smart_manager.chanel.model.ChannelBotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChannelBotRepository extends JpaRepository<ChannelBot, UUID> {

    Optional<ChannelBot> findByBotNameAndType(String botName, ChannelBotType channelType);
}
