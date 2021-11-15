package by.morunov.controller;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Ticket;
import by.morunov.service.TicketService;
import by.morunov.service.impl.TicketServiceImpl;
import by.morunov.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
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
    private final TicketServiceImpl ticketService;

    private final UserServiceImpl userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto){
        TicketDto newTicket = ticketService.addTicket(ticketDto);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        List<TicketDto> allTickets = ticketService.getAll();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Ticket> buyTicket(@RequestBody Ticket ticket, Principal principal){
        UserDto userDto = userService.getUserByEmail(principal.getName());
        List<Ticket> tickets = userDto.getTickets();
        tickets.add(ticket);
        userDto.setTickets(tickets);
        userService.updateUser(userService.converterToEntity(userDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

