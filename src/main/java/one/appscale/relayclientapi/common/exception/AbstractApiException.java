package one.appscale.relayclientapi.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractApiException extends ResponseStatusException {
    private static final long serialVersionUID = 577825321404747411L;

    public AbstractApiException(final HttpStatus status) {
        super(status);
    }

    public AbstractApiException(final HttpStatus status, final String reason) {
        super(status, reason);
    }

    public AbstractApiException(final HttpStatus status, final String reason, final Throwable cause) {
        super(status, reason, cause);
    }
}
