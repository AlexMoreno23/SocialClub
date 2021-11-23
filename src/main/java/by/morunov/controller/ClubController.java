package by.morunov.controller;

import by.morunov.domain.dto.ClubDto;
import by.morunov.service.impl.ClubServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@AllArgsConstructor
public class ClubController {
    private final ClubServiceImpl clubService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClubController.class);


    @GetMapping
    public ResponseEntity<List<ClubDto>> getAllClubs(){
        List<ClubDto> allClubs = clubService.getAll();
        return new ResponseEntity<>(allClubs, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ClubDto> addNewClub(@RequestBody ClubDto clubDto){
        LOGGER.info("Add new club {}", clubDto.getNameTeam());
        ClubDto newClub = clubService.addClub(clubDto);
        return new ResponseEntity<>(newClub, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClubDto> deleteClub(@PathVariable("id") Long id){
        LOGGER.info("Delete club");
        clubService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
