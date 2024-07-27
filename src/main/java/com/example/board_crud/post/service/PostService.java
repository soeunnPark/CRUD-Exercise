package com.example.board_crud.post.service;

import com.example.board_crud.post.PostRepository;
import com.example.board_crud.post.dto.PostRequest;
import com.example.board_crud.post.entity.PostEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Builder
public class PostService {


    private final PostRepository postRepository;


    public PostEntity createPost(PostRequest postRequest) {
        PostEntity post = PostEntity.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();
        postRepository.save(post);
        return post;
    }

    public PostEntity updatePost(Long postId, PostRequest postRequest) {
        PostEntity post = postRepository.findById(postId).orElseThrow();
        // 수정할 제목 또는 내용이 없을 경우에는 원본이 유지된다.
        Optional.ofNullable(postRequest.getTitle()).ifPresent(post::setTitle);
        Optional.ofNullable(postRequest.getContent()).ifPresent(post::setContent);


        postRepository.save(post);
        return post;
    }

    public Optional<PostEntity> findPost(Long postId) {
        return postRepository.findById(postId);
    }

    public Optional<List<PostEntity>> findAllPost() {
        return Optional.of(postRepository.findAll());
    }

    public void deletePost(Long postId) {

        PostEntity post = postRepository.findById(postId).orElseThrow();

        postRepository.delete(post);
    }
}


