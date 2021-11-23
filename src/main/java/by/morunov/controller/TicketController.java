package by.morunov.controller;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Ticket;
import by.morunov.service.TicketService;
import by.morunov.service.converter.TicketConverter;
import by.morunov.service.impl.MatchServiceImpl;
import by.morunov.service.impl.TicketServiceImpl;
import by.morunov.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Controller
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
    private final MatchServiceImpl matchService;
    private final TicketServiceImpl ticketService;
    private final UserServiceImpl userService;
    private final TicketConverter ticketConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<TicketDto> addTicket(Long matchId){
        TicketDto ticketDto = new TicketDto();
        ticketDto.setMatch(matchService.getMatch(matchId));
        LOGGER.info("Add ticket {}", ticketDto);
        TicketDto newTicket = ticketService.addTicket(ticketDto);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        List<TicketDto> allTickets = ticketService.getAll();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Ticket> buyTicket(@RequestBody TicketDto ticket, Principal principal){
        UserDto userDto = userService.getUserByEmail(principal.getName());
        List<TicketDto> tickets = userDto.getTickets();
        tickets.add(ticket);
        userDto.setTickets(tickets);
        userService.saveUser(userDto);
        LOGGER.info("User {} buy ticket {}", userDto, ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

