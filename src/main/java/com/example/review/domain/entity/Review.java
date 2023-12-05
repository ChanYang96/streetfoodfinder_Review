package com.example.review.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReviewId;

    @Column(columnDefinition = "NVARCHAR(30) NOT NULL") //30자 제한
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")//반드시 기입
    private String content;

    @Column(columnDefinition = "TEXT NOT NULL")//반드시 기입
    private String checklist;

    @Column(columnDefinition = "TEXT NOT NULL")//반드시 기입
    private String weather;

    @NotNull //날짜 관련
    private LocalDateTime createDate;

    /*@Lob //1126 이미지 업로드
    @Column(name = "photo")
    private byte[] photo;

    @Column(columnDefinition = "TEXT") //1204 추가
    private String photoUrl;*/
}
