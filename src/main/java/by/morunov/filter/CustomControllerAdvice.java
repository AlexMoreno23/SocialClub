package by.morunov.filter;

import by.morunov.domain.dto.ErrorRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class CustomControllerAdvice {
    private final static Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRsp errorResponse(MethodArgumentNotValidException ex) {
        logger.warn("Cannot process request", ex);
        return new ErrorRsp("Request contains invalid arguments", 400);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorRsp errorResponse(Exception ex) {
        return new ErrorRsp(ex.getMessage(), 500);
    }
}
