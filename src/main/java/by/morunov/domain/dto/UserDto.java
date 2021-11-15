package by.morunov.domain.dto;

import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.Role;
import by.morunov.domain.entity.Ticket;
import by.morunov.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private int balance;
    public List<Ticket> tickets;
    private Club team;
    private Set<Role> roles;
    private List<User> friends;
}
