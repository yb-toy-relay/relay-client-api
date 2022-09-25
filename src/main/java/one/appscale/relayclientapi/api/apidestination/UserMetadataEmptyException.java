package one.appscale.relayclientapi.api.apidestination;

import one.appscale.relayclientapi.common.exception.AbstractClientException;

import java.io.Serial;

public class UserMetadataEmptyException extends AbstractClientException {
    @Serial
    private static final long serialVersionUID = 148872192314820951L;

    public UserMetadataEmptyException(final String objectKey) {
        super("objectKey:" + objectKey);
    }
}
