package by.morunov.domain.dto;

import by.morunov.domain.entity.Club;
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
public class MatchDto {

    private Long id;
    private Club team1;
    private Club team2;
    private String dateOfMatch;

}
