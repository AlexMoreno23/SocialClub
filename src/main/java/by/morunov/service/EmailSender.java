package by.morunov.service;

/**
 * @author Alex Morunov
 */
public interface EmailSender {
    void send(String to, String email);
}
