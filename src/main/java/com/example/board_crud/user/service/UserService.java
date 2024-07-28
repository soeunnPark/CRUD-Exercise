package com.example.board_crud.user.service;

import com.example.board_crud.user.UserRepository;
import com.example.board_crud.user.dto.LoginRequest;
import com.example.board_crud.user.dto.RegisterRequest;
import com.example.board_crud.user.dto.UserResponse;
import com.example.board_crud.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserResponse register(RegisterRequest registerRequest) {

        UserEntity user = UserEntity.builder()
                .userId(registerRequest.getUserId())
                .userName(registerRequest.getUserName())
                .userPassword(passwordEncoder.encode(registerRequest.getUserPassword())) // 비밀번호 해시 처리
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();

    }

    public UserResponse login(LoginRequest loginRequest) {


        return userRepository.findByUserId(loginRequest.getUserId())
                .filter(user -> passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword()))
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("로그인 실패"));
    }
}
