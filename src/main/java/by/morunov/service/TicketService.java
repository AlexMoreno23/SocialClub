package by.morunov.service;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.entity.Match;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface TicketService {

    TicketDto addTicket(TicketDto ticketDto);
    TicketDto getTicketById(Long id);
    List<TicketDto> getAll();
    List<TicketDto> getAllByMatch(Match match);
    void delete(Long id);

}
