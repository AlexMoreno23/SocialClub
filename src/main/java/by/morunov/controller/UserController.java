package by.morunov.controller;

import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.User;
import by.morunov.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Controller
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userServiceImpl;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserDto>> allUsers() {
        List<UserDto> allUsers = userServiceImpl.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{team}")
    public ResponseEntity<List<UserDto>> allUserByTeam(@PathVariable("team") Club team) {
        List<UserDto> allUserByTeam = userServiceImpl.getByTeam(team);
        return new ResponseEntity<>(allUserByTeam, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userById(@PathVariable("id") Long id) {
        UserDto user = userServiceImpl.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUserById(id);
        LOGGER.info("User with ID {} delete", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PutMapping("/{userId}/add/{friendsID}")
    public void addFriends(@PathVariable Long userId,
                           @PathVariable Long friendsID){
        userServiceImpl.addFriends(userId, friendsID);
    }


    @PutMapping("/{id}/balance")
    public void topBalance(@PathVariable Long id, @RequestBody UserDto user) {
        UserDto updateUser = userServiceImpl.getUserById(id);
        updateUser.setBalance(user.getBalance() + updateUser.getBalance());
        LOGGER.info("User with Id {}, top to balance on {}$ ", id, user.getBalance());
        userServiceImpl.saveUser(updateUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @RequestBody UserDto user) {
        UserDto updateUser = userServiceImpl.getUserById(id);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        userServiceImpl.saveUser(updateUser);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
