package by.morunov.service;

import by.morunov.domain.dto.MatchDto;
import by.morunov.domain.entity.Club;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface MatchService {

    MatchDto addMatch(MatchDto matchDto);

    MatchDto getMatch(Long id);

    List<MatchDto> getAll();

    List<MatchDto> getAllByTeamName(Club team);


}
