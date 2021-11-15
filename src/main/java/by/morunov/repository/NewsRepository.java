package by.morunov.repository;

import by.morunov.domain.entity.News;
import by.morunov.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

     List<News> findAllByAuthor(User user);
     List<News> findAllByTitleLike(String title);




}
