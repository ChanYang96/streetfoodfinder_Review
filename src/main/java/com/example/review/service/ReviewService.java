package com.example.review.service;

import com.example.review.configuration.S3Config;
import com.example.review.domain.entity.Review;
import com.example.review.domain.form.ReviewForm;
import com.example.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewrepository;
    private final S3Config amazonS3Client;

    @Transactional
    public void createreview(ReviewForm reviewform){

        Review review = new Review();
        review.setTitle(reviewform.getTitle());
        review.setContent(reviewform.getContent());
        review.setChecklist(reviewform.getChecklist());
        review.setWeather(reviewform.getWeather());

        review.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)); //현재 날짜 자동
        reviewrepository.save(review);
    }
}
