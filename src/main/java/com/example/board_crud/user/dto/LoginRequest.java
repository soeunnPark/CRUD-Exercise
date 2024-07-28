package com.example.board_crud.user.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String userPassword;

}
