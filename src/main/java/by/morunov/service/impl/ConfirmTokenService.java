package by.morunov.service.impl;

import by.morunov.domain.entity.ConfirmToken;
import by.morunov.repository.ConfirmTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
@AllArgsConstructor
public class ConfirmTokenService {

    private final ConfirmTokenRepository confirmTokenRepository;

    public void saveConfirmToken(ConfirmToken token){
        confirmTokenRepository.save(token);
    }
    public Optional<ConfirmToken> getToken(String token) {
        return confirmTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
