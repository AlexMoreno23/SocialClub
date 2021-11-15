package by.morunov.exception;

/**
 * @author Alex Morunov
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
