package by.morunov.service.converter;

import by.morunov.domain.dto.ClubDto;
import by.morunov.domain.entity.Club;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class ClubConverter implements Converter<Club, ClubDto> {

    @Override
    public Club toEntity(ClubDto clubDto) {
        Club club = new Club();
        club.setId(clubDto.getId());
        club.setNameTeam(clubDto.getNameTeam());
        return club;
    }

    @Override
    public List<Club> toEntity(List<ClubDto> clubDtos) {
        return clubDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public ClubDto toDto(Club club) {
        ClubDto clubDto = new ClubDto();
        clubDto.setId(club.getId());
        clubDto.setNameTeam(club.getNameTeam());
        return clubDto;
    }

    @Override
    public List<ClubDto> toDto(List<Club> clubs) {
        return clubs.stream().map(this::toDto).collect(Collectors.toList());
    }
}
