package cz.erstegroup.example.demo.controller.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ValidationException;
import java.io.IOException;

/**
 * Controller for handling custom exceptions.
 */
@ControllerAdvice
public class ErrorHandlerController {
    /**
     * Handles ValidationException and set http status to 400.
     */
    @ExceptionHandler(ValidationException.class)
    public void handleConstraintViolationException(final ValidationException exception, final ServletWebRequest webRequest) throws IOException {
        assert webRequest.getResponse() != null;
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    /**
     * Handles IllegalArgumentException and set http status to 400.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(final IllegalArgumentException exception, final ServletWebRequest webRequest) throws IOException {
        assert webRequest.getResponse() != null;
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    /**
     * Handles Exception and set http status to 503.
     */
    @ExceptionHandler(Exception.class)
    public void handleIOException(final Exception exception, final ServletWebRequest webRequest) throws Exception {
        assert webRequest.getResponse() != null;
        webRequest.getResponse().sendError(HttpStatus.SERVICE_UNAVAILABLE.value());
    }
}
