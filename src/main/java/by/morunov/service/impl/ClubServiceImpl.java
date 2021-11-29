package by.morunov.service.impl;

import by.morunov.domain.dto.ClubDto;
import by.morunov.domain.entity.Club;
import by.morunov.repository.ClubRepository;
import by.morunov.service.ClubService;
import by.morunov.service.converter.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private Converter<Club, ClubDto> clubConverter;

    @Override
    public ClubDto addClub(ClubDto clubDto) {
        return clubConverter.toDto(clubRepository.save(clubConverter.toEntity(clubDto)));
    }

    @Override
    public List<ClubDto> getAll() {
        return clubConverter.toDto(Lists.newArrayList(clubRepository.findAll()));
    }

    @Override
    public ClubDto getByNameTeam(String nameTeam) {
        return clubConverter.toDto(clubRepository.findByNameTeam(nameTeam));
    }

    @Override
    public void deleteById(Long id) {
        clubRepository.deleteById(id);

    }
}
