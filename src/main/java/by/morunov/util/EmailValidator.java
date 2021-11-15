package by.morunov.util;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * @author Alex Morunov
 */
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // TODO: Regex to validate email
        return true;
    }
}
