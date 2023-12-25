package com.example.review.service;

import com.example.review.configuration.S3Config;
import com.example.review.domain.entity.Review;
import com.example.review.domain.form.ReviewForm;
import com.example.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewrepository;
    private final S3Config amazonS3Client;
    private final UploadPhotoService uploadPhotoService;

    public void CreateReview(ReviewForm reviewform) {

        Review review = new Review();
        review.setTitle(reviewform.getTitle());
        review.setContent(reviewform.getContent());
        review.setChecklist(reviewform.getChecklist());
        review.setWeather(reviewform.getWeather());
        review.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)); //현재 날짜 자동

        reviewrepository.save(review);

    }

    public void updateReview(Long reviewId, ReviewForm reviewForm) {
        Review existingReview = reviewrepository.findById(reviewId) //리뷰 아이디로 찾는다. 추후 이메일 같은 고유 값으로 수정
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        // 엔티티 클래스 내에 로직을 두지 않고 ReviewService에서 처리
        existingReview.setTitle(reviewForm.getTitle());
        existingReview.setContent(reviewForm.getContent());
        existingReview.setChecklist(reviewForm.getChecklist());
        existingReview.setWeather(reviewForm.getWeather());
        existingReview.setUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)); //수정할 항목들

        reviewrepository.save(existingReview);
    }


}
