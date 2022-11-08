package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.media.MediaDto;
import com.instantpic.coreservice.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
public class MediaController {
    private MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService){
        this.mediaService = mediaService;
    }

    @PostMapping ("/mediaUpload")
    public MediaDto mediaUpload(@RequestBody MediaDto mediaDraft){
        MediaDto media = mediaService.mediaUploadService(mediaDraft);
        return media;
    }

    @GetMapping("/mediaShow")
    public MediaDto mediaShow(int articleId){
        MediaDto media = mediaService.mediaShowService(articleId);
        return media;
    }

    @PutMapping("/mediaDelete")
    public MediaDto mediaDelete(int articleId, int mediaId, String url){
        MediaDto media = mediaService.mediaDeleteService(articleId, mediaId, url);
        return media;
    }
}
