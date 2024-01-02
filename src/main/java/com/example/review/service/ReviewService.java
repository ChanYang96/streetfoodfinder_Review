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

    /*public void CreateReview(ReviewForm reviewform) { //1226 수정 전

        Review review = new Review();
        review.setTitle(reviewform.getTitle());
        review.setContent(reviewform.getContent());
        review.setChecklist(reviewform.getChecklist());
        review.setWeather(reviewform.getWeather());
        review.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)); //현재 날짜 자동

        reviewrepository.save(review);
    }*/

    public void CreateReview(ReviewForm reviewform, MultipartFile image) throws IOException {
        Review review = new Review();
        //Review review = null;
        if (reviewform != null) { //리뷰 폼에서 입력 받은 값이 존재 한다면 업로드
            //review = new Review();
            //Review review = new Review();
            review.setTitle(reviewform.getTitle());
            review.setContent(reviewform.getContent());
            review.setChecklist(reviewform.getChecklist());
            review.setWeather(reviewform.getWeather());
            review.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)); // 현재 날짜 자동
            reviewrepository.save(review);
        }


        if (image != null) {
            uploadPhotoService.upload(image, review.getReviewId());
        }
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

    public void deleteReview(Long reviewId) { //0102 삭제 기능
        // 리뷰 ID를 통해 리뷰를 찾기
        Review review = reviewrepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 없습니다. id=" + reviewId));

        // DB에서 리뷰를 삭제
        reviewrepository.delete(review);

        uploadPhotoService.delete(reviewId); //이미지 삭제 메소드 호출
    }
}
