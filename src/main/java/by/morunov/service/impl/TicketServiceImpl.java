package by.morunov.service.impl;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.entity.Match;
import by.morunov.domain.entity.Ticket;
import by.morunov.exception.TicketFoundException;
import by.morunov.repository.TicketRepository;
import by.morunov.service.TicketService;
import by.morunov.service.converter.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private Converter<Ticket, TicketDto> ticketConverter;

    private final static String TICKET_NOT_FOUND_ID = "ticket with id %s not found";




    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        TicketDto addTicket = null;
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 30; j++)
            addTicket = ticketConverter.toDto(ticketRepository.save(ticketConverter.toEntity(new TicketDto(
                        ticketDto.getMatch(), i, j, ticketDto.getPrice()))));
        }

        return addTicket;
    }

    @Override
    public TicketDto getTicketById(Long id) {
        return ticketConverter.toDto(ticketRepository.findById(id).orElseThrow(
                () -> new TicketFoundException(String.format(TICKET_NOT_FOUND_ID, id))));
    }

    @Override
    public List<TicketDto> getAll() {
        return ticketConverter.toDto(Lists.newArrayList(ticketRepository.findAll()));
    }

    @Override
    public List<TicketDto> getAllByMatch(Match match) {
        return ticketConverter.toDto(Lists.newArrayList(ticketRepository.findAllByMatch(match)));
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);

    }
}
