package one.appscale.relayclientapi.api._support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.exception.ExceptionUtils.getMessage;

@Slf4j
public abstract class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex,
                                                             final Object body,
                                                             final HttpHeaders headers,
                                                             final HttpStatus status, final WebRequest request) {
        log(ex, request, status);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        log(ex, request, status);
        final ErrorMessage errorMessage = new ErrorMessage(ex.getMostSpecificCause().getMessage());
        final var response = new ErrorResponse(status, errorMessage);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
                                                                          final HttpHeaders headers,
                                                                          final HttpStatus status,
                                                                          final WebRequest request) {
        log(ex, request, status);
        final var errors = List.of(new ErrorMessage(ex.getMessage()));
        final var response = new ErrorResponse(status, errors);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex,
                                                         final HttpHeaders headers,
                                                         final HttpStatus status,
                                                         final WebRequest request) {
        return handleBindExceptions(ex, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        return handleBindExceptions(ex, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
                                                                     final HttpHeaders headers,
                                                                     final HttpStatus status,
                                                                     final WebRequest request) {
        log(ex, request, status);
        final ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        final var response = new ErrorResponse(status, errorMessage);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    private ResponseEntity<Object> handleBindExceptions(final BindException ex,
                                                        final HttpStatus status,
                                                        final WebRequest request) {
        log(ex, request, status);
        final var errors = ex.getBindingResult()
                             .getFieldErrors()
                             .stream()
                             .map(error -> new ErrorMessage(error.getField(), error.getDefaultMessage()))
                             .collect(toList());
        final var response = new ErrorResponse(status, errors);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    protected void log(final Throwable throwable, final WebRequest request, final HttpStatus status) {
        if (status.is4xxClientError()) {
            logWarn(throwable, request, status);
        } else if (status.is5xxServerError()) {
            logError(throwable, request, status);
        }
    }

    protected Map<String, String> extractHeaders(final HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                          .stream()
                          .collect(toMap(h -> h, request::getHeader));
    }

    protected void logWarn(final Throwable throwable, final WebRequest request, final HttpStatus status) {
        final var servletRequest = ((ServletWebRequest) request).getRequest();
        log.warn("status:{}, message:{}, path:{}, headers:{}",
                 status,
                 getMessage(throwable),
                 servletRequest.getRequestURI(),
                 extractHeaders(servletRequest));
    }

    protected void logError(final Throwable throwable, final WebRequest request, final HttpStatus status) {
        final var servletRequest = ((ServletWebRequest) request).getRequest();
        log.error("status:{}, message:{}, path:{}, headers:{}",
                  status,
                  getMessage(throwable),
                  servletRequest.getRequestURI(),
                  extractHeaders(servletRequest),
                  throwable);
    }

    protected void logErrorWithResponseName(final String responseName,
                                            final Throwable throwable,
                                            final WebRequest request,
                                            final HttpStatus status) {
        final var servletRequest = ((ServletWebRequest) request).getRequest();
        log.error("responseName: {}, status:{}, message:{}, path:{}, headers:{}",
                  responseName,
                  status,
                  getMessage(throwable),
                  servletRequest.getRequestURI(),
                  extractHeaders(servletRequest),
                  throwable);
    }
}
