package com.example.board_crud.post.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PostRequest {

    private String title;

    private String content;
}
