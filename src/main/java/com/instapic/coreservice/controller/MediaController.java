package com.instapic.coreservice.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.instapic.coreservice.dto.media.MediaDto;
import com.instapic.coreservice.dto.media.MediaList;
import com.instapic.coreservice.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {
    private MediaService mediaService;
    private AmazonS3Client amazonS3Client;
    private String S3Bucket = "instapic-media";

    @Autowired
    public MediaController(MediaService mediaService, AmazonS3Client amazonS3Client){
        this.mediaService = mediaService;
        this.amazonS3Client = amazonS3Client;
    }

    @PostMapping (consumes = {"multipart/form-data"})
    public MediaList mediaUpload(int articleId, @RequestPart List<String> mentions, @RequestPart MultipartFile multipartFile) throws IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String originalName = multipartFile.getOriginalFilename() + articleId + timestamp.getTime(); // 파일 이름
        long size = multipartFile.getSize(); // 파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);

        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // 접근가능한 URL 가져오기

        MediaList media = mediaService.mediaUploadService(imagePath, mentions ,articleId);
        return media;
    }

    @GetMapping
    public MediaList mediaShow(int articleId){
        MediaList media = mediaService.mediaShowService(articleId);
        return media;
    }


    @DeleteMapping
    public MediaList mediaDelete(Optional<Integer> articleId, Optional<Integer> mediaId) {
        MediaList result = mediaService.mediaDeleteService(articleId, mediaId);
        try {
            for (MediaDto media : result.getMedia()) {
                amazonS3Client.deleteObject(S3Bucket, URLDecoder.decode(media.getUrl().replace("https://instapic-media.s3.ap-northeast-2.amazonaws.com/", ""), "UTF-8"));
            }
            return result;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
    }
}
