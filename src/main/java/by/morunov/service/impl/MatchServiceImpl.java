package by.morunov.service.impl;

import by.morunov.domain.dto.MatchDto;
import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.Match;
import by.morunov.repository.MatchRepository;
import by.morunov.service.MatchService;
import by.morunov.service.converter.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private Converter<Match, MatchDto> matchConverter;


    @Override
    public MatchDto addMatch(MatchDto matchDto) {
        return matchConverter.toDto(matchRepository.save(matchConverter.toEntity(matchDto)));
    }

    @Override
    public MatchDto getMatch(Long id) {
        return matchConverter.toDto(matchRepository.getById(id));
    }

    @Override
    public List<MatchDto> getAll() {
        return matchConverter.toDto(Lists.newArrayList(matchRepository.findAll()));
    }

    @Override
    public List<MatchDto> getAllByTeamName(Club team) {
        List<MatchDto> homeMatches = matchConverter.toDto(Lists.newArrayList(matchRepository.findByTeam1(team)));
        List<MatchDto> awayMatches = matchConverter.toDto(Lists.newArrayList(matchRepository.findByTeam2(team)));
        return Stream.concat(homeMatches.stream(), awayMatches.stream()).collect(Collectors.toList());
    }

}
