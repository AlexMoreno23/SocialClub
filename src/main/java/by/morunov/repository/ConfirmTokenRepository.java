package by.morunov.repository;

import by.morunov.domain.entity.ConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Alex Morunov
 */
@Repository
public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {


    Optional<ConfirmToken> findByToken(String token);

    @Modifying
    @Query("UPDATE ConfirmToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
