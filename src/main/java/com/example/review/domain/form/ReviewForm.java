package com.example.review.domain.form;

import com.example.review.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewForm {
    private String title; //제목
    private String content; //내용
    private String checklist; //체크리스트
    private String weather; //날씨

    public static Review from (ReviewForm from){
        return Review.builder()
                .title(from.getTitle())
                .content(from.getContent())
                .checklist(from.getChecklist())
                .weather(from.getWeather())
                //.updateDate(LocalDateTime.now())
                //.createDate(LocalDateTime.now())
                .build();
    }
}
