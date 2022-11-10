package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.media.MediaDto;
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
    public List<MediaDto> mediaShow(int articleId){
        List<MediaDto> media = mediaService.mediaShowService(articleId);
        return media;
    }

    //media 하나만 삭제
    @DeleteMapping("/media")
    public MediaDto mediaSeparateDelete(int articleId, int mediaId){
        MediaDto media = mediaService.mediaSeparateDeleteService(articleId, mediaId);
        return media;
    }

    //해당 article 포함 media 전체삭제
    @DeleteMapping("/media")
    public MediaDto mediaALlDelete(int articleId){
        MediaDto media = mediaService.mediaAllDeleteService(articleId);
        return media;
    }
}
