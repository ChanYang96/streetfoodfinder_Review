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

    @PostMapping("/create-review")
    public ResponseEntity<?> createReview(@RequestBody ReviewForm reviewForm) {
        reviewservice.createReview(reviewForm);
        return ResponseEntity.ok("리뷰 작성 완료");
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // 이미지를 업로드하고 해당 URL을 반환
        String imageUrl = uploadPhotoService.upload(file, "review-images");
        return ResponseEntity.ok(imageUrl);
    }

    /*@PostMapping("/create-review")
    public ResponseEntity<?> createReview(@ModelAttribute ReviewForm form) {
        reviewservice.createReview(form);
        return ResponseEntity.ok("리뷰 작성 완료");
    }*/

    /*@PostMapping("/create-review")
    public ResponseEntity<?> CreateReview(@ModelAttribute ReviewForm form) {
        reviewservice.createreview(form);
        return ResponseEntity.ok("리뷰 작성 완료");
    }

    @PostMapping("/create-review-with-image")
    public ResponseEntity<?> CreateReviewWithImage(
            @ModelAttribute ReviewForm form,
            @RequestParam("image") MultipartFile image
    ) {
        reviewservice.createreview(form, image);
        return ResponseEntity.ok("리뷰 작성 완료");
    }*/

    /*@PostMapping("/create-review") //1204 버전
    public ResponseEntity<?> CreateReview(@ModelAttribute ReviewForm form){
        reviewservice.createreview(form);
        return ResponseEntity.ok("리뷰 작성 완료");
    }*/

    /*@PostMapping("/create-review")
    public ResponseEntity<?> CreateReview(@RequestPart("form") ReviewForm form,@RequestPart("image") MultipartFile image){
        reviewservice.createreview(form, image);
        return ResponseEntity.ok("리뷰 작성 완료");
    }*/

    /*public ResponseEntity<String> CreateReview(@RequestBody ReviewForm form){
        reviewservice.createreview(form);
        return ResponseEntity.ok("리뷰 작성 완료");
    }*/
}
