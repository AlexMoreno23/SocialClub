package by.morunov.repository;

import by.morunov.domain.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Club findByNameTeam(String nameTeam);
    List<Club> findAll();
    void deleteById(Long id);

}
