package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.media.MediaDto;
import com.instantpic.coreservice.dto.media.MediaList;
import com.instantpic.coreservice.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {
    private MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService){
        this.mediaService = mediaService;
    }

    @PostMapping ("/media")
    public MediaDto mediaUpload(@RequestBody MediaDto mediaDraft){
        MediaDto media = mediaService.mediaUploadService(mediaDraft);
        return media;
    }

    @GetMapping("/media")
    public MediaList mediaShow(int articleId){
        MediaList media = mediaService.mediaShowService(articleId);
        return media;
    }


    @DeleteMapping("/media")
    public MediaDto mediaDelete(int articleId, int mediaId) {
        MediaDto media = mediaService.mediaDeleteService(articleId, mediaId);
        return media;
    }
}
