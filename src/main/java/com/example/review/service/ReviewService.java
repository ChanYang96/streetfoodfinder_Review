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

    /*@Transactional
    public void createreview(ReviewForm reviewform, MultipartFile image){
        String photoUrl = null;

        if (reviewform.getPhoto() != null && !reviewform.getPhoto().isEmpty()) {
            try {
                // 이미지 업로드 후 URL 받아오기
                photoUrl = uploadPhotoService.upload(reviewform.getPhoto(), "1.jpg");
            } catch (IOException e) {
                throw new RuntimeException("이미지 업로드에 실패했습니다.", e);
            }
        }

        // Review 엔터티 생성
        Review review = ReviewForm.createReview(reviewform, photoUrl);
        reviewrepository.save(review);
    }*/

    /*@Transactional
    public void createreview(ReviewForm reviewform){

        Review review = new Review();
        review.setTitle(reviewform.getTitle());
        review.setContent(reviewform.getContent());
        review.setChecklist(reviewform.getChecklist());
        review.setWeather(reviewform.getWeather());

        if (reviewform.getPhoto() != null && !reviewform.getPhoto().isEmpty()) {
            try {
                byte[] photoBytes = reviewform.getPhoto().getBytes();
                review.setPhoto(photoBytes);
            } catch (IOException e) {
                // 이미지 업로드 실패 시 예외 처리
                throw new RuntimeException("이미지 업로드에 실패했습니다.", e);
            }
        }

        review.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)); //현재 날짜 자동
        reviewrepository.save(review);
    }*/
    }
}
