package com.example.board_crud.post.controller;

import com.example.board_crud.post.dto.PostRequest;
import com.example.board_crud.post.entity.PostEntity;
import com.example.board_crud.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostEntity> create(@RequestBody PostRequest postRequest) {

        return ResponseEntity.ok().body(postService.createPost(postRequest));
    }


}
