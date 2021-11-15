package by.morunov.repository;

import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {


    List<Match> findByTeam1(Club team);
    List<Match> findByTeam2(Club team);

 }



