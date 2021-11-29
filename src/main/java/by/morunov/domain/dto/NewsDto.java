package by.morunov.domain.dto;

import by.morunov.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alex Morunov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsDto {
    private Long id;
    private String title;
    private String text;
    private User author;
    private String publicationDate;
}
