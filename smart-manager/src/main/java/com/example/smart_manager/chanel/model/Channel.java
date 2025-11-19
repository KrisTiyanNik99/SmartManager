package com.example.smart_manager.chanel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"bot_name", "type"}))
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "bot_name", nullable = false)
    private String botName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelType type;

    @Column(nullable = false)
    private String authToken;

    @OneToMany(mappedBy = "channel")
    private Set<ChannelTarget> channelTargets = new HashSet<>();

    private boolean isActive;
}
