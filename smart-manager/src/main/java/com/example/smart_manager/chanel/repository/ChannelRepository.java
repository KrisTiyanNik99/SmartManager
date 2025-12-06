package com.example.smart_manager.chanel.repository;

import com.example.smart_manager.chanel.model.Channel;
import com.example.smart_manager.chanel.model.ChannelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, UUID> {

    Optional<Channel> findByBotNameAndType(String botName, ChannelType channelType);
}
