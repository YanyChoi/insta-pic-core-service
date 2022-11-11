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

    public MediaList mediaDeleteService(int articleId, int mediaId){
        MediaList result = new MediaList();
        if(articleId == NULL) {
            result.setMedia(mediaRepository.deleteSeperateMedia(mediaId));
            result.setCount(result.getMedia().size());
            return result;
        }
        else{
            result.setMedia(mediaRepository.deleteAllMedia(articleId));
            result.setCount(result.getMedia().size());
            return result;
        }
    }

    /*public MediaDto mediaAllDeleteService(int articleId){
        MediaDto result;
        result = mediaRepository.deleteAllMedia(articleId).get();
        return result;
    }*/

    public MediaList mediaUploadService(MediaDto media){
        MediaList result = new MediaList();
        boolean upload = mediaRepository.uploadMedia(media);
        if (upload){
            result.setMedia(mediaRepository.readMediaByArticleId(media.getArticleId()));
        }
        result.setCount(result.getMedia().size());
        return result;
    }
}
