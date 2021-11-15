package by.morunov.service.impl;

import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.Role;
import by.morunov.domain.entity.Ticket;
import by.morunov.domain.entity.User;
import by.morunov.exception.EmailException;
import by.morunov.exception.TicketStoreException;
import by.morunov.exception.UserNotFoundException;
import by.morunov.exception.UserRegistrationException;
import by.morunov.domain.entity.ConfirmToken;
import by.morunov.repository.UserRepository;
import by.morunov.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Alex Morunov
 */
@Transactional
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ConfirmTokenService confirmTokenService;

    private final static String USER_NOT_FOUND_EMAIL = "user with email %s not found";
    private final static String USER_NOT_FOUND_ID = "user with id %s not found";



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_EMAIL, email)));
    }

    public String signUpUser(User user){
        boolean userExist = userRepository.findByEmail(user.getEmail()).
                isPresent();
        if(userExist){
            throw new EmailException("email already taken");
        }
        String encodePassword = passwordEncoder.
                encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        // TODO: Send confirmation token
        ConfirmToken confirmToken = new ConfirmToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmTokenService.saveConfirmToken(confirmToken);

        // TODO: Send email

        return token;

    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }



    public void addUserFromGoogle(User user) throws UserRegistrationException {
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb.isEmpty()) {
            user.setRoles(Collections.singleton(Role.PERSON));
            user.setEnabled(true);
            userRepository.save(user);
        }

    }

    public boolean addFriends(User user, User user1) {
        List<User> users = user.getFriends();
        users.add(user1);
        user.setFriends(users);
        userRepository.save(user);
        return true;

    }

    public List<UserDto> getByTeam(Club team){
        return userConverter.toDto(userRepository.findAllByTeam(team));
    }

    public Optional<User> getUserByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    public List<UserDto> getAll() {
        return userConverter.toDto(userRepository.findAll());
    }

    public UserDto getByUsername(String username){
        return userConverter.toDto(userRepository.findByUsername(username));
    }

    public UserDto getUserById(Long id) {
        return userConverter.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ID, id))));
    }
    public UserDto converterToDto(User user){
        return userConverter.toDto(user);
    }

    public User converterToEntity(UserDto userDto){
        return userConverter.toEntity(userDto);
    }



    public UserDto updateUser(User user){
        return userConverter.toDto(userRepository.save(user));
    }



    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public UserDto buyTicket(User user, Ticket ticket){
        int balance = user.getBalance() - ticket.getPrice();
        if (balance < 0){
            throw new TicketStoreException("not enough funds! Top up your balance");
        }
        user.setBalance(balance);
        List<Ticket> tickets = user.getTickets();
        tickets.add(ticket);
        user.setTickets(tickets);
        return userConverter.toDto(userRepository.save(user));
    }

    public UserDto getUserByEmail(String email){
        return userConverter.toDto(userRepository.findByEmail(email).orElseThrow(()
                -> new UserNotFoundException(String.format(USER_NOT_FOUND_EMAIL, email))));
    }


}
