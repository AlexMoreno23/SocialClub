package by.morunov.controller;

import by.morunov.domain.dto.NewsDto;
import by.morunov.domain.entity.User;
import by.morunov.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Morunov
 */

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsServiceImpl newsService;

    @Autowired
    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews(){
        List<NewsDto> allNews = newsService.getAll();
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }

    @GetMapping("/{author}")
    public ResponseEntity<List<NewsDto>> getAllNewsByAuthor(@PathVariable("author") User user){
        List<NewsDto> newsByAuthor = newsService.getByAuthor(user);
        return new ResponseEntity<>(newsByAuthor, HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<NewsDto>> getByTitle(@PathVariable("title") String title){
        List<NewsDto> newsByTitle = newsService.getByTitle(title);
        return new ResponseEntity<>(newsByTitle, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsDto> addNews(@AuthenticationPrincipal User user, @RequestBody NewsDto newsDto){
        User author  = new User();
        // TODO: change setters
        author.setId(user.getId());
        author.setFirstName(user.getFirstName());
        author.setLastName(user.getLastName());
        author.setRoles(user.getRoles());
        newsDto.setAuthor(author);
        NewsDto newNews = newsService.addNews(newsDto);
        return new ResponseEntity<>(newNews, HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<NewsDto> deleteNews(@PathVariable("id") Long id){
        newsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
   }




}
