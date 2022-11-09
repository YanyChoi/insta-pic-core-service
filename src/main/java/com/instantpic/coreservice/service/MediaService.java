package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.media.MediaDto;
import com.instantpic.coreservice.repository.MediaRepository;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
    private MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public MediaDto mediaShowService(int articleId){
        MediaDto result;
        result = mediaRepository.readMediaByArticleId(articleId).get();
        return result;
    }

    public MediaDto mediaDeleteService(int articleId, int mediaId, String url){
        MediaDto result;
        result = mediaRepository.deleteMedia(articleId, mediaId, url).get();
        return result;
    }

    public MediaDto mediaUploadService(MediaDto media){
        MediaDto result;
        if (mediaRepository.uploadMedia(media)){
            result = mediaRepository.readMediaByArticleId(media.getArticleId()).get();
        }
        else{
            result = new MediaDto();
            result.setUrl("Upload Fail");
        }
        return result;
    }
}
