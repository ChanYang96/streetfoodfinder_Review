package com.example.review.domain.form;

import com.example.review.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewForm {
    private String title; //제목
    private String content; //내용
    private String checklist; //체크리스트
    private String weather; //날씨
    private MultipartFile photo; //1126 이미지 필드

    public static Review toEntity(ReviewForm form, byte[] photoBytes) {
        return Review.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .checklist(form.getChecklist())
                .weather(form.getWeather())
                .photo(photoBytes) // 이미지 데이터 추가
                .build();
    }

    /*public static Review createReview(ReviewForm form, String photoUrl) { //1204 수정
        return Review.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .checklist(form.getChecklist())
                .weather(form.getWeather())
                .photoUrl(photoUrl)
                .createDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS))
                .build();
    }*/
    /*public static Review ReviewForm (ReviewForm from){
        return Review.builder()
                .title(from.getTitle())
                .content(from.getContent())
                .checklist(from.getChecklist())
                .weather(from.getWeather())
                .build();
    }*/
}
