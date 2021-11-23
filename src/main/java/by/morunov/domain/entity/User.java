package by.morunov.domain.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Alex Morunov
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "google_id", updatable = false)
    private String googleId;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password", updatable = false)
    private String password;

    @Column(name = "balance")
    private Long balance;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", updatable = false)
    private Club team;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", updatable = false)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "friends", updatable = false)
    private List<User> friends;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", updatable = false)
    private List<Ticket> tickets;

    @Column(name = "activation", updatable = false)
    private boolean enabled;


    public User( String firstName, String lastName, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
