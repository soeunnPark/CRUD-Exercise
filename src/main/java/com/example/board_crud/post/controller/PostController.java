package com.example.board_crud.post.controller;

import com.example.board_crud.post.dto.PostRequest;
import com.example.board_crud.post.entity.PostEntity;
import com.example.board_crud.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<PostEntity> create(@RequestBody PostRequest postRequest) {

        return ResponseEntity.ok().body(postService.createPost(postRequest));
    }

    // Patch 사용한 이유: 일부를 수정하기 위함
    @PatchMapping("/{postId}")
    public ResponseEntity<PostEntity> update(
            @PathVariable Long postId,
            @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok().body(postService.updatePost(postId, postRequest));
    }


    @GetMapping("/{postId}")
    public ResponseEntity<Optional<PostEntity>> read(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok().body(postService.findPost(postId));
    }

    @GetMapping("")
    public ResponseEntity<Optional<List<PostEntity>>> readAll() {
        return ResponseEntity.ok().body(postService.findAllPost());
    }


    @DeleteMapping("{postId}")
    public void delete(
            @PathVariable Long postId
    ) {
        postService.deletePost(postId);
    }

}


