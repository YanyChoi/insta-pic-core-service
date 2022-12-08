package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.media.MediaDto;
import com.instantpic.coreservice.dto.media.MediaList;
import com.instantpic.coreservice.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

    public MediaList mediaDeleteService(Optional<Integer> articleId, Optional<Integer> mediaId){
        MediaList result = new MediaList();
        if (articleId.isEmpty()) {
            result.setMedia(mediaRepository.deleteSeparateMedia(mediaId.get().intValue()));
        }
        else {
            result.setMedia(mediaRepository.deleteAllMedia(articleId.get().intValue()));
        }
        result.setCount(result.getMedia().size());
        return result;
    }
    public MediaList mediaUploadService(String url, List<String> mentions ,int articleId){
        MediaList result = new MediaList();
        String cdnUrl = url.replace("https://instapic-media.s3.ap-northeast-2.amazonaws.com/", "https://ds27ztqt2rer0.cloudfront.net/");
        boolean upload = mediaRepository.uploadMedia(cdnUrl, mentions ,articleId);
        if (upload){
            result.setMedia(mediaRepository.readMediaByArticleId(articleId));
        }
        result.setCount(result.getMedia().size());
        return result;
    }
}
