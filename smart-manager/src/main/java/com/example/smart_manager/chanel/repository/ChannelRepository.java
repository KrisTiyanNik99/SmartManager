package com.example.smart_manager.chanel.repository;

import com.example.smart_manager.chanel.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, UUID> {
}
