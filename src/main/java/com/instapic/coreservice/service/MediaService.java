package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.Media;
import com.instapic.coreservice.domain.MediaMention;
import com.instapic.coreservice.domain.UserInfo;
import com.instapic.coreservice.dto.request.media.MediaMentionPostRequestDto;
import com.instapic.coreservice.dto.request.media.MediaPostRequestDto;
import com.instapic.coreservice.repository.media.FileType;
import com.instapic.coreservice.repository.media.MediaMentionRepository;
import com.instapic.coreservice.repository.media.MediaRepository;
import com.instapic.coreservice.repository.amazon.AmazonS3Repository;
import com.instapic.coreservice.repository.article.ArticleRepository;
import com.instapic.coreservice.repository.user.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("aws.yaml")
public class MediaService {

    private final UserInfoRepository userInfoRepository;
    private final ArticleRepository articleRepository;
    private final MediaRepository mediaRepository;
    private final MediaMentionRepository mediaMentionRepository;
    private final AmazonS3Repository amazonS3Repository;

    @Value("${MEDIA_BUCKET_URL}")
    private String mediaBucketUrl;

    @Value("${THUMBNAIL_BUCKET_URL}")
    private String thumbnailBucketUrl;
    @Transactional
    public void uploadMedia(Long articleId, MediaPostRequestDto mediaDto, List<MediaMentionPostRequestDto> mentionsDto, MultipartFile file) throws IOException, NoSuchElementException {
        String url = amazonS3Repository.uploadObject(file, FileType.MEDIA);
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId));
        Media media = Media.builder()
                .url(mediaBucketUrl + "/" + FileType.MEDIA + "/" + url)
                .mediaFormat(mediaDto.getMediaFormat())
                .article(article)
                .thumbnail(thumbnailBucketUrl + "/" + mediaDto)
                .build();
        mediaRepository.save(media);

        for (MediaMentionPostRequestDto mentionDto : mentionsDto) {
            log.info(mentionDto.toString());
            UserInfo userInfo = userInfoRepository.findById(mentionDto.getUserId()).orElseThrow(() -> new NoSuchElementException("No such user with ID " + mentionDto.getUserId()));
            MediaMention mention = MediaMention.builder()
                    .media(media)
                    .user(userInfo)
                    .xPosition(mentionDto.getX())
                    .yPosition(mentionDto.getY())
                    .build();
            mediaMentionRepository.save(mention);
        }
    }
    @Transactional
    public void deleteMedia(Long mediaId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new NoSuchElementException("No such media with ID " + mediaId));
        amazonS3Repository.deleteObject(FileType.MEDIA, media.getUrl().replace(mediaBucketUrl, ""));
        mediaRepository.delete(media);
    }

}
