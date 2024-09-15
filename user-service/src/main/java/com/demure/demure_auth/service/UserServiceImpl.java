package com.demure.demure_auth.service;

import com.demure.demure_auth.entity.DTO;
import com.demure.demure_auth.entity.User;
import com.demure.demure_auth.utility.UserMapper;
import com.demure.demure_auth.repository.UserRepository;
import com.demure.demure_auth.auth.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    private final UserMapper userMapper;

    @Override
    public DTO registerUser(DTO DTO) {
        User user = userMapper.toUser(DTO);
        user.setPassword(passwordEncoder.encode(DTO.password()));
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

   public String authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return tokenUtil.generateToken(String.valueOf(user));
        }
        return null;
    }

    @Override
    public DTO getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(userMapper::toUserDto).orElse(null);
    }

    @Override
    public DTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserDto(user);
    }
}
