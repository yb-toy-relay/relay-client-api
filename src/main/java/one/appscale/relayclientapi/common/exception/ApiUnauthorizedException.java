package one.appscale.relayclientapi.common.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiUnauthorizedException extends AbstractClientException {
    @Serial
    private static final long serialVersionUID = -712611299133165559L;

    public ApiUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public ApiUnauthorizedException(final String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }
}
