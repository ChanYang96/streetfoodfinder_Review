package com.example.review.controller;

import com.example.review.domain.form.ReviewForm;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewservice;

    @PostMapping("/create-review")
    public ResponseEntity<String> review(@RequestBody ReviewForm form){
        reviewservice.createreview(form);
        return ResponseEntity.ok("리뷰 작성 완료");
    }
}
