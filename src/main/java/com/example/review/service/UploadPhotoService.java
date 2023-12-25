package com.example.review.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class UploadPhotoService { //이미지 업로드 관련 서비스
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 업로드된 파일의 순서
    private static AtomicInteger uploadCounter = new AtomicInteger(0);

    public String upload(MultipartFile multipartFile) throws IOException {
        // 업로드 날짜
        LocalDateTime uploadDateTime = LocalDateTime.now();
        int uploadOrder = uploadCounter.incrementAndGet();

        //버킷에 저장되는 이미지 이름 지정
        String s3FileName = uploadOrder + "_" + uploadDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")) + ".jpg";

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

}
