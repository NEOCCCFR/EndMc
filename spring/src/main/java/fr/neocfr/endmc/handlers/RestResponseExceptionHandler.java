package fr.neocfr.endmc.handlers;

import fr.neocfr.endmc.exceptions.CreationError;
import fr.neocfr.endmc.exceptions.ExecutionError;
import fr.neocfr.endmc.exceptions.NotFoundError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreationError.class)
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, new RestResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ExecutionError.class)
    public ResponseEntity<Object> handleExecution(RuntimeException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, new RestResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthentication(RuntimeException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, new RestResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, new RestResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
