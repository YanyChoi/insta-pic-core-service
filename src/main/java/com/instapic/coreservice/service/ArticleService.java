package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.UserInfo;
import com.instapic.coreservice.domain.like.ArticleLike;
import com.instapic.coreservice.dto.request.article.ArticlePostRequestDto;
import com.instapic.coreservice.dto.response.article.ArticleDetailResponseDto;
import com.instapic.coreservice.dto.response.article.ArticlePreviewResponseDto;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.repository.articleLike.ArticleLikeRepository;
import com.instapic.coreservice.repository.user.UserInfoRepository;
import com.instapic.coreservice.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserInfoRepository userInfoRepository;
    private final ArticleLikeRepository articleLikeRepository;

    public ArticlePreviewResponseDto createArticle(Long authorId, ArticlePostRequestDto articlePostRequestDto) throws NoSuchElementException {
        UserInfo author = userInfoRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + authorId));
        Article article = Article.builder()
                .author(author)
                .location(articlePostRequestDto.getLocation())
                .text(articlePostRequestDto.getText())
                .build();
        articleRepository.save(article);
        return article.toPreviewDto();
    }

    public List<ArticleDetailResponseDto> getFeedArticles(Long userId, Optional<Long> lastArticleId, int size) throws NoSuchElementException{
        return articleRepository.findFeedArticles(userId, lastArticleId, size).stream().map(Article::toDetailedDto).toList();
    }

    public List<ArticlePreviewResponseDto> getUserArticles(Long userId, Optional<Long> lastArticleId, int size) throws NoSuchElementException{
        return articleRepository.findUserArticles(userId, lastArticleId, size).stream().map(Article::toPreviewDto).toList();
    }

    public List<ArticlePreviewResponseDto> getLocationArticles(String location, Optional<Long> lastArticleId, int size) throws NoSuchElementException{
        return articleRepository.findLocationArticles(location, lastArticleId, size).stream().map(Article::toPreviewDto).toList();
    }

    public ArticleDetailResponseDto getArticleById(Long articleId) throws NoSuchElementException {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId))
                .toDetailedDto();
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public void createArticleLike(Long articleId, Long userId) throws NoSuchElementException, Exception {
        if (articleLikeRepository.isArticleLikedByUser(articleId, userId)) {
            throw new Exception("This article is already liked by this user.");
        }
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId));
        ArticleLike articleLike = ArticleLike.builder()
                .article(article)
                .user(userInfo)
                .build();
        article.increaseLikeCount();
        articleLikeRepository.save(articleLike);
    }
    public void deleteArticleLike(Long articleId, Long userId) throws NoSuchElementException {
        articleLikeRepository.deleteArticleLikeByUserIdAndArticleId(userId, articleId);
    }

    public List<UserPreviewResponseDto> getArticleLikes(Long articleId, Optional<Long> lastUserId, int size) {
        return articleLikeRepository.findArticleLikesByArticleId(articleId, lastUserId, size)
                .stream().map(UserInfo::toPreviewDto)
                .toList();
    }

}
