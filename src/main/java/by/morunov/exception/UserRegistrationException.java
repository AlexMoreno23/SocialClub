package by.morunov.exception;

import java.sql.SQLException;

/**
 * @author Alex Morunov
 */
public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException(String message) {
        super(message);
    }


}
