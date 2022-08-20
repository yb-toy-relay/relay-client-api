package one.appscale.relayclientapi.common.exception;

import org.springframework.http.HttpStatus;

public class ApiUnauthorizedException extends AbstractClientException {

    public ApiUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public ApiUnauthorizedException(final String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }
}
