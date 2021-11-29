package by.morunov.service;

import by.morunov.domain.dto.NewsDto;
import by.morunov.domain.entity.User;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface NewsService {

    NewsDto addNews(NewsDto newsDto);

    List<NewsDto> getAll();

    List<NewsDto> getByAuthorOrTitle(String authorFirstName, String title);

    List<NewsDto> getByTitle(String title);

    void deleteById(Long id);

}
