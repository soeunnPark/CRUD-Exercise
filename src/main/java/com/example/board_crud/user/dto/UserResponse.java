package com.example.board_crud.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userId;
    private String userName;
}