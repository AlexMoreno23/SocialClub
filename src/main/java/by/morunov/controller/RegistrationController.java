package by.morunov.controller;

import by.morunov.domain.entity.RegistrationRequest;
import by.morunov.service.impl.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Morunov
 */
@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);


    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        LOGGER.info("Registration request");
        return registrationService.register(request);
    }

    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token ){
        return registrationService.confirmToken(token);
    }
}
