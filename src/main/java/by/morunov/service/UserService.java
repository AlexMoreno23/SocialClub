package by.morunov.service;

import by.morunov.domain.dto.TicketDto;
import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.User;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface UserService {

    String signUpUser(User user);
    void addUserFromGoogle(User user);
    void addFriends(Long userId, Long friendId);
    List<UserDto> getByTeam(Club team);
    UserDto userAuth(String auth);
    void saveUser(UserDto userDto);
    void deleteUserById(Long id);
    void buyTicket(UserDto user, TicketDto ticket);
    UserDto getUserByEmail(String email);
    void TopBalance(Long id, Long addBalance);

}
