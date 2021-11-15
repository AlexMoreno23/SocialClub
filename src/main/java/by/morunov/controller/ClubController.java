package by.morunov.controller;

import by.morunov.domain.dto.ClubDto;
import by.morunov.service.impl.ClubServiceImpl;
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
@RequestMapping("/api/club")
public class ClubController {
    private final ClubServiceImpl clubService;

    @Autowired
    public ClubController(ClubServiceImpl clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubDto>> getAllClubs(){
        List<ClubDto> allClubs = clubService.getAll();
        return new ResponseEntity<>(allClubs, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ClubDto> addNewClub(@RequestBody ClubDto clubDto){
        ClubDto newClub = clubService.addClub(clubDto);
        return new ResponseEntity<>(newClub, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClubDto> deleteClub(@PathVariable("id") Long id){
        clubService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
