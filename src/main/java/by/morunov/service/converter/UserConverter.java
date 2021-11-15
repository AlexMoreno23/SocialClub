package by.morunov.service.converter;

import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Ticket;
import by.morunov.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Morunov
 */
@Component
public class UserConverter implements Converter<User, UserDto>{
    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setGoogleId(user.getGoogleId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBalance(userDto.getBalance());
        user.setBalance(userDto.getBalance());
        user.setTickets(userDto.getTickets());
        user.setRoles(userDto.getRoles());
        user.setFriends(userDto.getFriends());
        return user;
    }

    @Override
    public List<User> toEntity(List<UserDto> userDtos) {
        return userDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setGoogleId(user.getGoogleId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setTeam(user.getTeam());
        userDto.setBalance(user.getBalance());
        userDto.setTickets(user.getTickets());
        userDto.setRoles(user.getRoles());
        userDto.setFriends(user.getFriends());
        return userDto;
    }

    @Override
    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
