package com.example.board_crud.post.entity;

import com.example.board_crud.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.ErrorResponse;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;

}
