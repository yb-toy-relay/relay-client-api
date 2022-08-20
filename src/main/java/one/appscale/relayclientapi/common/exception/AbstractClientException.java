package one.appscale.relayclientapi.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractClientException extends AbstractApiException {
    private static final long serialVersionUID = -7327308638925450325L;

    public AbstractClientException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public AbstractClientException(final String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public AbstractClientException(final HttpStatus status) {
        super(status);
    }

    public AbstractClientException(final HttpStatus status, final String reason) {
        super(status, reason);
    }

    public AbstractClientException(final HttpStatus status, final String reason, final Throwable cause) {
        super(status, reason, cause);
    }
}