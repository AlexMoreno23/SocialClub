package by.morunov.service.impl;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.*;
import by.morunov.exception.EmailException;
import by.morunov.exception.TicketStoreException;
import by.morunov.exception.UserNotFoundException;
import by.morunov.exception.UserRegistrationException;
import by.morunov.repository.UserRepository;
import by.morunov.service.UserService;
import by.morunov.service.converter.UserConverter;
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

public class UserServiceImpl implements UserDetailsService, UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final ConfirmTokenService confirmTokenService;

    private final static String USER_NOT_FOUND_EMAIL = "user with email %s not found";
    private final static String USER_NOT_FOUND_ID = "user with id %s not found";

    public UserServiceImpl(UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder,
                           UserConverter userConverter,
                           ConfirmTokenService confirmTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
        this.confirmTokenService = confirmTokenService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_EMAIL, email)));
    }

    public String signUpUser(User user) {
        boolean userExist = userRepository.findByEmail(user.getEmail()).
                isPresent();
        if (userExist) {
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


    public List<UserDto> getByTeam(Club team) {
        return userConverter.toDto(userRepository.findAllByTeam(team));
    }

    public UserDto getUserByGoogleId(String googleId) {
        return userConverter.toDto(userRepository.findByGoogleId(googleId));
    }

    public UserDto userAuth(String auth) {
        UserDto userAuth = new UserDto();
        if (userRepository.findByGoogleId(auth) != null) {
            UserDto userDto = getUserByGoogleId(auth);
            userAuth.setId(userDto.getId());
            userAuth.setUsername(userDto.getUsername());
            userAuth.setEmail(userDto.getEmail());
            userAuth.setFirstName(userDto.getFirstName());
            userAuth.setLastName(userDto.getLastName());
            userAuth.setBalance(userDto.getBalance());
            userAuth.setFriends(userDto.getFriends());
            userAuth.setTeam(userDto.getTeam());
            userAuth.setRoles(userDto.getRoles());
            return userAuth;
        }
        Optional<User> user = userRepository.findByEmail(auth);
        userAuth.setId(user.get().getId());
        userAuth.setUsername(user.get().getUsername());
        userAuth.setFirstName(user.get().getFirstName());
        userAuth.setLastName(user.get().getLastName());
        userAuth.setBalance(user.get().getBalance());
        userAuth.setEmail(user.get().getEmail());
        userAuth.setFriends(userConverter.toDto(user.get().getFriends()));
        userAuth.setTeam(user.get().getTeam());
        userAuth.setRoles(user.get().getRoles());
        return userAuth;
    }

    public List<UserDto> getAll() {
        return userConverter.toDto(userRepository.findAll());
    }

    public UserDto getUserById(Long id) {
        return userConverter.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ID, id))));
    }


    public User converterToEntity(UserDto userDto) {
        return userConverter.toEntity(userDto);
    }


    public void saveUser(UserDto userDto){
        userConverter.toDto(userRepository.save(userConverter.toEntity(userDto)));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void addFriends(Long userId, Long friendId){
        User user = userRepository.getById(userId);
        User friend = userRepository.getById(friendId);
        List<User> friendsList = user.getFriends();
        friendsList.add(friend);
        user.setFriends(friendsList);
        userRepository.save(user);
    }

    public void buyTicket(UserDto user, TicketDto ticket) {
        Long balance = user.getBalance() - ticket.getPrice();
        if (balance < 0) {
            throw new TicketStoreException("not enough funds! Top up your balance");
        }
        user.setBalance(balance);
        List<TicketDto> tickets = user.getTickets();
        tickets.add(ticket);
        user.setTickets(tickets);
         userRepository.save(userConverter.toEntity(user));
    }

    public void TopBalance(Long id, Long addBalance){
        UserDto userDto = userConverter.toDto(userRepository.getById(id));
        Long result = userDto.getBalance() + addBalance;
        userDto.setBalance(result);
        userRepository.save(converterToEntity(userDto));
    }

    public UserDto getUserByEmail(String email) {
        return userConverter.toDto(userRepository.findByEmail(email).orElseThrow(()
                -> new UserNotFoundException(String.format(USER_NOT_FOUND_EMAIL, email))));
    }


}
