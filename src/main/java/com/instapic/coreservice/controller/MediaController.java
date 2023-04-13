package com.instapic.coreservice.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.instapic.coreservice.dto.media.MediaDto;
import com.instapic.coreservice.dto.media.MediaList;
import com.instapic.coreservice.dto.request.media.MediaPostRequestDto;
import com.instapic.coreservice.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/article/{articleId}/media")
    public ResponseEntity<Void> mediaUpload(@PathVariable Long articleId, @RequestPart MediaPostRequestDto body, @RequestPart MultipartFile file) throws IOException {
        mediaService.uploadMedia(articleId, body, file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/media/{mediaId}")
    public ResponseEntity<Void> mediaDelete(@PathVariable Long mediaId) {
        mediaService.deleteMedia(mediaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
