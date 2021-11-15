package by.morunov.service;

import by.morunov.domain.dto.ClubDto;
import by.morunov.domain.entity.Club;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface ClubService {

    ClubDto addClub(ClubDto clubDto);
    List<ClubDto> getAll();
    ClubDto getByNameTeam(String nameTeam);
    void deleteById(Long id);
}
