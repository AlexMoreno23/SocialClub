package by.morunov.exception;



/**
 * @author Alex Morunov
 */
public class TicketFoundException extends RuntimeException {

    public TicketFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketFoundException(String message) {
        super(message);
    }
}
