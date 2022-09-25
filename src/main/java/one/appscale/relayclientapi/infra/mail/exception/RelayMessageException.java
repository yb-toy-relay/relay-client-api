package one.appscale.relayclientapi.infra.mail.exception;

import one.appscale.relayclientapi.common.exception.AbstractServerException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class RelayMessageException extends AbstractServerException {
    @Serial
    private static final long serialVersionUID = -4538504306920004162L;

    public RelayMessageException(final String message, final Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }
}
