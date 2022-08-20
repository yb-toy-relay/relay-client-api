package one.appscale.relayclientapi.common.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public abstract class AbstractServerException extends AbstractApiException {
    @Serial
    private static final long serialVersionUID = 3761445845513894873L;

    public AbstractServerException() {
        super(INTERNAL_SERVER_ERROR);
    }

    public AbstractServerException(final String reason) {
        super(INTERNAL_SERVER_ERROR, reason);
    }

    public AbstractServerException(final HttpStatus status) {
        super(status);
    }

    public AbstractServerException(final HttpStatus status, final String reason) {
        super(status, reason);
    }

    public AbstractServerException(final HttpStatus status, final String reason, final Throwable cause) {
        super(status, reason, cause);
    }
}
