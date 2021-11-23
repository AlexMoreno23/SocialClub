package by.morunov.service.converter;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.entity.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
@AllArgsConstructor
public class TicketConverter implements Converter<Ticket, TicketDto>{
    private final MatchConverter matchConverter;

    @Override
    public Ticket toEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setMatch(matchConverter.toEntity(ticketDto.getMatch()));
        ticket.setRow(ticketDto.getRow());
        ticket.setSeat(ticketDto.getSeat());
        ticket.setPrice(ticketDto.getPrice());
        return ticket;
    }

    @Override
    public List<Ticket> toEntity(List<TicketDto> ticketDtos) {
        return ticketDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public TicketDto toDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setMatch(matchConverter.toDto(ticket.getMatch()));
        ticketDto.setRow(ticket.getRow());
        ticketDto.setSeat(ticket.getSeat());
        ticketDto.setPrice(ticket.getPrice());
        return ticketDto;
    }

    @Override
    public List<TicketDto> toDto(List<Ticket> tickets) {
        return tickets.stream().map(this::toDto).collect(Collectors.toList());
    }
}
