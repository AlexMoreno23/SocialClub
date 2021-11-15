package by.morunov.controller;

import by.morunov.domain.dto.MatchDto;
import by.morunov.domain.entity.Club;
import by.morunov.service.MatchService;
import by.morunov.service.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Controller
@RequestMapping("/api/match")
public class MatchController {
    private final MatchServiceImpl matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllMatches(){
        List<MatchDto> allMatches = matchService.getAll();
        return new ResponseEntity<>(allMatches, HttpStatus.OK);
    }

    @GetMapping("/{team}")
    public ResponseEntity<List<MatchDto>> getAllMatchesByTeam(@PathVariable("team")Club team){
        List<MatchDto> allMatchesByTeam = matchService.getAllByTeamName(team);
        return new ResponseEntity<>(allMatchesByTeam, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MatchDto> addMatch(@RequestBody MatchDto matchDto){
        MatchDto newMatch = matchService.addMatch(matchDto);
        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> matchById(@PathVariable("id") Long id){
        MatchDto oneMatch = matchService.getMatch(id);
        return new ResponseEntity<>(oneMatch, HttpStatus.OK);

    }



}
