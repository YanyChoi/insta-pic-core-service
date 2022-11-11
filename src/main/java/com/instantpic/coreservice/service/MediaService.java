package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.media.MediaDto;
import com.instantpic.coreservice.dto.media.MediaList;
import com.instantpic.coreservice.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.sql.Types.NULL;

@Service
public class MediaService {
    private MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public MediaList mediaShowService(int articleId){
        // List<MediaDto> result = (List<MediaDto>) mediaRepository.readMediaByArticleId(articleId).get();
        MediaList result = new MediaList();
        result.setMedia(mediaRepository.readMediaByArticleId(articleId));
        result.setCount(result.getMedia().size());
        return result;
    }

    public MediaDto mediaDeleteService(int articleId, int mediaId){
        MediaDto result;
        if(articleId == NULL) {
            result = mediaRepository.deleteSeperateMedia(mediaId).get();
            return result;
        }
        else{
            result = mediaRepository.deleteAllMedia(articleId);
            return result;
        }
    }

    /*public MediaDto mediaAllDeleteService(int articleId){
        MediaDto result;
        result = mediaRepository.deleteAllMedia(articleId).get();
        return result;
    }*/

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
