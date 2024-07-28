package com.example.board_crud.user.controller;

import com.example.board_crud.user.dto.LoginRequest;
import com.example.board_crud.user.dto.RegisterRequest;
import com.example.board_crud.user.dto.UserResponse;
import com.example.board_crud.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {

        return ResponseEntity.ok().body(userService.register(registerRequest));


    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Validated
            @RequestBody LoginRequest loginRequest,
            HttpSession httpSession
    ) {
        UserResponse userResponse = userService.login(loginRequest);

        httpSession.setAttribute("userName", userResponse.getUserName());
        httpSession.setAttribute("userId", userResponse.getUserId());
        httpSession.setAttribute("id", userResponse.getId());


        return ResponseEntity.ok().body(userResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
