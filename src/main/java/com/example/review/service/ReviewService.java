package com.example.review.service;

import com.example.review.domain.entity.Review;
import com.example.review.domain.form.ReviewForm;
import com.example.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewrepository;

    @Transactional
    public void createreview(ReviewForm reviewform){
        String title = reviewform.getTitle();
        String content = reviewform.getContent();
        String checklist = reviewform.getChecklist();
        String weather = reviewform.getWeather();

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setChecklist(checklist);
        review.setWeather(weather);
        reviewrepository.save(review);
    }
}
