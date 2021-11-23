package by.morunov.domain.dto;

import by.morunov.domain.entity.Match;
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
public class TicketDto {

    private Long id;
    private MatchDto match;
    private int row;
    private int seat;
    private int price;

    public TicketDto(MatchDto match, int row, int seat, int price) {
        this.match = match;
        this.row = row;
        this.seat = seat;
        this.price = price;
    }
}
