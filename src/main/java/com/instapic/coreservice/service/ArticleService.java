package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.domain.like.ArticleLike;
import com.instapic.coreservice.dto.request.article.ArticlePostRequestDto;
import com.instapic.coreservice.dto.response.article.ArticleDetailResponseDto;
import com.instapic.coreservice.dto.response.article.ArticlePreviewResponseDto;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.repository.ArticleLikeRepository;
import com.instapic.coreservice.repository.UserRepository;
import com.instapic.coreservice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleLikeRepository articleLikeRepository;

    @Transactional
    public ArticlePreviewResponseDto createArticle(Long authorId, ArticlePostRequestDto articlePostRequestDto) throws NoSuchElementException {
        User author = userRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + authorId));
        Article article = Article.builder()
                .author(author)
                .location(articlePostRequestDto.getLocation())
                .text(articlePostRequestDto.getText())
                .build();
        articleRepository.save(article);
        return article.toPreviewDto();
    }

    public List<ArticleDetailResponseDto> getFeedArticles(Long userId, Long lastArticleId, int size) throws NoSuchElementException{
        return articleRepository.findFeedArticles(userId, lastArticleId, size).stream().map(Article::toDetailedDto).toList();
    }

    public List<ArticlePreviewResponseDto> getUserArticles(Long userId, Long lastArticleId, int size) throws NoSuchElementException{
        return articleRepository.findUserArticles(userId, lastArticleId, size).stream().map(Article::toPreviewDto).toList();
    }

    public List<ArticlePreviewResponseDto> getLocationArticles(String location, Long lastArticleId, int size) throws NoSuchElementException{
        return articleRepository.findLocationArticles(location, lastArticleId, size).stream().map(Article::toPreviewDto).toList();
    }

    public ArticleDetailResponseDto getArticleById(Long articleId) throws NoSuchElementException {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId))
                .toDetailedDto();
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @Transactional
    public void createArticleLike(Long articleId, Long userId) throws NoSuchElementException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId));
        ArticleLike articleLike = ArticleLike.builder()
                .article(article)
                .user(user)
                .build();
        articleLikeRepository.save(articleLike);
    }
    @Transactional
    public void deleteArticleLike(Long articleId, Long userId) throws NoSuchElementException {
        articleLikeRepository.deleteArticleLikeByUserIdAndArticleId(userId, articleId);
    }

    public List<UserPreviewResponseDto> getArticleLikes(Long articleId, Long lastUserId, int size) {
        return articleLikeRepository.findArticleLikesByArticleId(articleId, lastUserId, size)
                .stream().map(User::toPreviewDto)
                .toList();
    }

}
