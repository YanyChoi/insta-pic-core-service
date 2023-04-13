package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.Media;
import com.instapic.coreservice.domain.MediaMention;
import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.dto.request.media.MediaMentionPostRequestDto;
import com.instapic.coreservice.dto.request.media.MediaPostRequestDto;
import com.instapic.coreservice.repository.*;
import com.instapic.coreservice.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@PropertySource("aws.yaml")
public class MediaService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final MediaRepository mediaRepository;
    private final MediaMentionRepository mediaMentionRepository;
    private final AmazonS3Repository amazonS3Repository;

    @Value("${MEDIA_BUCKET_URL}")
    private String mediaBucketUrl;

    @Value("${THUMBNAIL_BUCKET_URL}")
    private String thumbnailBucketUrl;
    public void uploadMedia(Long articleId, MediaPostRequestDto dto, MultipartFile file) throws IOException, NoSuchElementException {
        String url = amazonS3Repository.uploadObject(file, FileType.MEDIA);
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId));
        Media media = Media.builder()
                .url(mediaBucketUrl + "/" + dto.getMediaFormat() + "/" + url)
                .mediaFormat(dto.getMediaFormat())
                .article(article)
                .thumbnail(thumbnailBucketUrl + "/" + dto)
                .build();
        mediaRepository.save(media);

        for (MediaMentionPostRequestDto mentionDto : dto.getMentions()) {
            User user = userRepository.findById(mentionDto.getUserId()).orElseThrow(() -> new NoSuchElementException("No such user with ID " + mentionDto.getUserId()));
            MediaMention mention = MediaMention.builder()
                    .media(media)
                    .user(user)
                    .xPosition(mentionDto.getXPosition())
                    .yPosition(mentionDto.getYPosition())
                    .build();
            mediaMentionRepository.save(mention);
        }
    }
    public void deleteMedia(Long mediaId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new NoSuchElementException("No such media with ID " + mediaId));
        amazonS3Repository.deleteObject(FileType.MEDIA, media.getUrl().replace(mediaBucketUrl, ""));
        mediaRepository.delete(media);
    }

}
