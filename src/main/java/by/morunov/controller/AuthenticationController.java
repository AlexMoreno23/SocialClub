package by.morunov.controller;

import by.morunov.domain.dto.UserDto;
import by.morunov.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Alex Morunov
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping
    public UserDto auth(Principal principal) {
        LOGGER.debug("User {} logged in", principal.getName());
        UserDto userDto = userService.getUserByEmail(principal.getName());
        UserDto userAuth = new UserDto();
        userAuth.setUsername(userDto.getUsername());
        userAuth.setEmail(userDto.getEmail());
        userAuth.setRoles(userDto.getRoles());
        return userAuth;
    }
}
