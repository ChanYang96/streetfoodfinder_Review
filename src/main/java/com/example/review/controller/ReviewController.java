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

    /*@PostMapping("/create-review")
    public ResponseEntity<?> createReviewWithImage(
            @RequestPart("form") ReviewForm form,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        reviewservice.CreateReview(form);
        uploadPhotoService.upload(image);
        return ResponseEntity.ok("리뷰 작성 완료");
    }*/

    @PostMapping("/create-review")
    public ResponseEntity<?> createReview(
            @RequestPart(value = "form", required = false) ReviewForm form,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        if (form == null && image == null) {
            return ResponseEntity.badRequest().body("리뷰 또는 이미지를 제공해야 합니다.");
        }

        if (form != null) { //리뷰를 작성 했다면
            reviewservice.CreateReview(form, image);
        } /*else { //이미지만 업로드 한다
            uploadPhotoService.upload(image);
        }*/

        return ResponseEntity.ok("리뷰 작성 완료");
    }

    @PutMapping("/update-review/{reviewId}")
    public ResponseEntity<?> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewForm form
    ) {
        reviewservice.updateReview(reviewId, form);
        return ResponseEntity.ok("리뷰 수정 완료");
    }

    @DeleteMapping("/delete-review/{reviewId}")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long reviewId
    ) {
        reviewservice.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰 및 이미지 삭제 완료");
    }
}
