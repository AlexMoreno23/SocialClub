package by.morunov.controller;

import by.morunov.domain.dto.UserDto;
import by.morunov.domain.entity.Club;
import by.morunov.domain.entity.User;
import by.morunov.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {
    private final UserServiceImpl userServiceImpl;


    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> allUsers(){
        List<UserDto> allUsers = userServiceImpl.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{team}")
    public ResponseEntity<List<UserDto>> allUserByTeam(@PathVariable("team") Club team){
        List<UserDto> allUserByTeam = userServiceImpl.getByTeam(team);
        return new ResponseEntity<>(allUserByTeam, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userById(@PathVariable("id") Long id){
        UserDto user = userServiceImpl.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id){
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user){
        UserDto updateUser = userServiceImpl.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
