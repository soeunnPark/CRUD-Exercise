package com.example.board_crud.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;

    @NotBlank
    private String userName;
}
