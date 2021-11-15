package by.morunov.service.converter;

import by.morunov.domain.dto.MatchDto;
import by.morunov.domain.entity.Match;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class MatchConverter implements Converter<Match, MatchDto> {
    @Override
    public Match toEntity(MatchDto matchDto) {
        Match match = new Match();
        match.setId(matchDto.getId());
        match.setTeam1(matchDto.getTeam1());
        match.setTeam2(matchDto.getTeam2());
        match.setDateOfMatch(matchDto.getDateOfMatch());
        return match;
    }

    @Override
    public List<Match> toEntity(List<MatchDto> matchDtos) {
        return matchDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public MatchDto toDto(Match match) {
        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setTeam1(match.getTeam1());
        matchDto.setTeam2(match.getTeam2());
        matchDto.setDateOfMatch(match.getDateOfMatch());
        return matchDto;
    }

    @Override
    public List<MatchDto> toDto(List<Match> matches) {
        return matches.stream().map(this::toDto).collect(Collectors.toList());
    }
}
