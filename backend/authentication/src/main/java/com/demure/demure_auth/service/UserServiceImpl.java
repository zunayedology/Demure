package com.demure.demure_auth.service;

import com.demure.demure_auth.dto.UserDto;
import com.demure.demure_auth.entity.User;
import com.demure.demure_auth.utility.UserMapper;
import com.demure.demure_auth.repository.UserRepository;
import com.demure.demure_auth.utility.TokenUtil;
import lombok.RequiredArgsConstructor;
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
    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return tokenUtil.generateToken(user);
            }
        }
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(userMapper::toUserDto).orElse(null);
    }
}
