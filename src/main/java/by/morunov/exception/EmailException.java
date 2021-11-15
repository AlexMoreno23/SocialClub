package by.morunov.exception;

/**
 * @author Alex Morunov
 */
public class EmailException extends IllegalStateException{
    public EmailException(String message) {
        super(message);
    }
}
