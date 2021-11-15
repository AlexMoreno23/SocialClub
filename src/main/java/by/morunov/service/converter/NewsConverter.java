package by.morunov.service.converter;

import by.morunov.domain.dto.NewsDto;
import by.morunov.domain.entity.News;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class NewsConverter implements Converter<News, NewsDto> {
    @Override
    public News toEntity(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setAuthor(newsDto.getAuthor());
        news.setPublicationDate(newsDto.getPublicationDate());
        return news;
    }

    @Override
    public List<News> toEntity(List<NewsDto> newsDtos) {
        return newsDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public NewsDto toDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setAuthor(news.getAuthor());
        news.setPublicationDate(newsDto.getPublicationDate());
        return newsDto;
    }

    @Override
    public List<NewsDto> toDto(List<News> news) {
        return news.stream().map(this::toDto).collect(Collectors.toList());
    }
}
