package by.morunov.service.impl;

import by.morunov.domain.dto.NewsDto;
import by.morunov.domain.entity.News;
import by.morunov.domain.entity.User;
import by.morunov.repository.NewsRepository;
import by.morunov.service.NewsService;
import by.morunov.service.converter.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Transactional
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private Converter<News, NewsDto> newsConverter;

    @Override
    public NewsDto addNews(NewsDto newsDto) {
        return newsConverter.toDto(newsRepository.save(newsConverter.toEntity(newsDto)));
    }

    @Override
    public List<NewsDto> getAll() {
        return newsConverter.toDto(Lists.newArrayList(newsRepository.findAll()));
    }

    @Override
    public List<NewsDto> getByAuthor(User user) {
        return newsConverter.toDto(Lists.newArrayList(newsRepository.findAllByAuthor(user)));
    }

    @Override
    public List<NewsDto> getByTitle(String title) {
        return newsConverter.toDto(Lists.newArrayList(newsRepository.findAllByTitleLike(title)));
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
