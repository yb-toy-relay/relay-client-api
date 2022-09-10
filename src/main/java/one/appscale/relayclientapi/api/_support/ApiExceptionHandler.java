package one.appscale.relayclientapi.api._support;

import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.common.exception.AbstractApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ApiResponseEntityExceptionHandler {
    @ExceptionHandler(AbstractApiException.class)
    protected ResponseEntity<Object> handleServerException(final AbstractApiException ex,
                                                           final WebRequest request) {
        log(ex, request, ex.getStatus());
        final var errorMessage = new ErrorMessage(ex.getClass().getSimpleName(), ex.getReason());
        final var response = new ErrorResponse(ex.getStatus(), List.of(errorMessage));
        return new ResponseEntity<>(response, ex.getResponseHeaders(), ex.getStatus());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Object> handleMissingRequestHeaderException(final MissingRequestHeaderException ex, final WebRequest request) {
        log(ex, request, BAD_REQUEST);
        final ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        final var response = new ErrorResponse(BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(response, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        log(ex, request, BAD_REQUEST);
        final var errors = ex.getConstraintViolations()
                             .stream()
                             .map(violation -> new ErrorMessage(violation.getPropertyPath().toString(), violation.getMessage()))
                             .collect(toList());

        final var response = new ErrorResponse(BAD_REQUEST, errors);
        return new ResponseEntity<>(response, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex,
                                                             final WebRequest request) {
        final var responseName = "UncaughtError";
        logErrorWithResponseName(responseName, ex, request, INTERNAL_SERVER_ERROR);
        final var error = new ErrorMessage(responseName, "The request is not available now.");
        final var response = new ErrorResponse(INTERNAL_SERVER_ERROR, error);
        return new ResponseEntity<>(response, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }
}
