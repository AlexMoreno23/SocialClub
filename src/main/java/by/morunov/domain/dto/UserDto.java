package by.morunov.domain.dto;

import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.Role;
import by.morunov.domain.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Morunov
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private Long id;
    private String googleId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long balance;
    public List<TicketDto> tickets;
    private Club team;
    private Set<Role> roles;
    private List<UserDto> friends;

    @JsonCreator
    public UserDto(Long balance) {
        this.balance = balance;
    }
}
