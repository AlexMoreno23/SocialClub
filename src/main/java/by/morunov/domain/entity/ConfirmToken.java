package by.morunov.domain.entity;

import by.morunov.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Alex Morunov
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmToken {

    @Id
    @SequenceGenerator(name = "confirm_token_sequence", sequenceName = "confirm_token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirm_token_sequence")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private User user;

    public ConfirmToken(String token,
                        LocalDateTime createdAt,
                        LocalDateTime expiredAt,
                        User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
