package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.request.media.MediaPostRequestDto;
import com.instapic.coreservice.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
