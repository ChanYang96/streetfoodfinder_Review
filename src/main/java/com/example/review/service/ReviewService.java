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

    public void CreateReview(ReviewForm reviewform, MultipartFile image) throws IOException {
        Review review = new Review();

        if (reviewform != null) { //폼 클래스에서 받은 값이 존재 한다면
            LocalDateTime createDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES); //작성한 날짜 지정
            review = ReviewForm.reviewfrom(reviewform, createDate); //폼 값과 시간 전달
            reviewrepository.save(review);
        }


        if (image != null) { //이미지 파일을 받은 것이 있다면
            uploadPhotoService.upload(image, review.getReviewId());
        }
    }

    public void updateReview(Long reviewId, ReviewForm reviewForm) {
        Review existingReview = reviewrepository.findById(reviewId) //리뷰 아이디로 찾는다.
                .orElseThrow(() -> new RuntimeException("해당 리뷰가 없습니다 id: " + reviewId));

        if (reviewForm != null) {
            existingReview.updateTitle(reviewForm.getTitle());
            existingReview.updateContent(reviewForm.getContent());
            existingReview.updateChecklist(reviewForm.getChecklist());
            existingReview.updateWeather(reviewForm.getWeather());
            existingReview.updateUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));

            reviewrepository.save(existingReview);
        }
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
