package com.example.board_crud.post.service;

import com.example.board_crud.post.PostRepository;
import com.example.board_crud.post.dto.PostRequest;
import com.example.board_crud.post.entity.PostEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class PostService {


    private final PostRepository postRepository;


    public PostEntity createPost(PostRequest postRequest) {
        return PostEntity.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();
    }

    public PostEntity updatePost(Long postId, PostRequest postRequest) {
        PostEntity newPost = postRepository.findById(postId).orElseThrow();


        // 원본 값을 기본으로 사용하고, DTO에서 새 값이 제공된 경우에만 업데이트
        String updatedTitle = (postRequest.getTitle() != null && !postRequest.getTitle().isEmpty())
                ? postRequest.getTitle()
                : newPost.getTitle();

        String updatedContent = (postRequest.getContent() != null && !postRequest.getContent().isEmpty())
                ? postRequest.getContent()
                : newPost.getContent();

        newPost.setTitle(updatedTitle);
        newPost.setContent(updatedContent);

        return postRepository.save(newPost);
    }
}
