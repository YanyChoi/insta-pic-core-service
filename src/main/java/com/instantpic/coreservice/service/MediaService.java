package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.media.MediaDto;
import com.instantpic.coreservice.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    private MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<MediaDto> mediaShowService(int articleId){
        List<MediaDto> result = (List<MediaDto>) mediaRepository.readMediaByArticleId(articleId).get();
        return result;
    }

    public MediaDto mediaSeperateDeleteService(int articleId, int mediaId){
        MediaDto result;
        result = mediaRepository.deleteSeperateMedia(articleId, mediaId).get();
        return result;
    }

    public MediaDto mediaAllDeleteService(int articleId){
        MediaDto result;
        result = mediaRepository.deleteAllMedia(articleId).get();
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
