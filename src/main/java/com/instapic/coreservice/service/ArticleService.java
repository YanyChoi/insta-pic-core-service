package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.dto.request.article.ArticlePostRequestDto;
import com.instapic.coreservice.dto.response.article.ArticleDetailResponseDto;
import com.instapic.coreservice.dto.response.article.ArticlePreviewResponseDto;
import com.instapic.coreservice.repository.UserRepository;
import com.instapic.coreservice.repository.ArticleRepository;
import com.instapic.coreservice.repository.revamped.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

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

    public Page<ArticleDetailResponseDto> getFeedArticles(Long userId, int offset, int size) throws NoSuchElementException{
        PageRequest pageRequest = PageRequest.of(offset, size);
        return articleRepository.findFeedArticles(userId, pageRequest).map(Article::toDetailedDto);
    }

    public Page<ArticleDetailResponseDto> getUserArticles(Long userId, int offset, int size) throws NoSuchElementException{
        PageRequest pageRequest = PageRequest.of(offset, size);
        return articleRepository.findUserArticles(userId, pageRequest).map(Article::toDetailedDto);
    }

    public Page<ArticleDetailResponseDto> getLocationArticles(String location, int offset, int size) throws NoSuchElementException{
        PageRequest pageRequest = PageRequest.of(offset, size);
        return articleRepository.findLocationArticles(location, pageRequest).map(Article::toDetailedDto);
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

}
