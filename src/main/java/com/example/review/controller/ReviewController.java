package com.example.review.controller;

import com.example.review.domain.form.ReviewForm;
import com.example.review.service.ReviewService;
import com.example.review.service.UploadPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewservice;
    private final UploadPhotoService uploadPhotoService;

    @PostMapping("/create-review-with-image")
    public ResponseEntity<?> createReviewWithImage(
            @RequestPart("form") ReviewForm form,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        reviewservice.CreateReview(form);
        uploadPhotoService.upload(image);
        return ResponseEntity.ok("리뷰 작성 완료");
    }
}
