package com.example.smart_manager.user.service;

import com.example.smart_manager.security.UserData;
import com.example.smart_manager.user.model.User;
import com.example.smart_manager.user.model.UserRole;
import com.example.smart_manager.user.repository.UserRepository;
import com.example.smart_manager.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private static final String USER_NOT_EXIST = "User with such id:[%s] doest not exist!";
    private static final String USER_ALREADY_EXIST = "User with such email:[%s] already exist!";
    private static final String USER_NOT_FOUND = "User with such email:[%s] is not found!";
    private static final String NEW_USER = "New User with username:[%s] and email:[%s] have been registered!";
    private static final String USER_LOGIN = "User with email:[%s] try to login!";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND.formatted(username)));

        log.info(USER_LOGIN.formatted(username));
        return new UserData(user.getId(), username, user.getPassword(), user.getRole(), user.isActive());
    }

    public void register(RegisterRequest userInfo) {
        Optional<User> optionalUser = userRepository.findByEmail(userInfo.getEmail());
        if (optionalUser.isPresent()) {
            throw new RuntimeException(USER_ALREADY_EXIST.formatted(userInfo.getEmail()));
        }

        User user = User.builder()
                .username(userInfo.getUsername())
                .email(userInfo.getEmail())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .role(UserRole.EMPLOYEE)
                .isActive(true)
                .build();
        userRepository.save(user);

        log.info(NEW_USER.formatted(user.getUsername(), user.getEmail()));
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(USER_NOT_EXIST.formatted(userId)));
    }
}
