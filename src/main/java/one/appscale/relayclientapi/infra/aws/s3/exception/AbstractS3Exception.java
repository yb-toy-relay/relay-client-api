package one.appscale.relayclientapi.infra.aws.s3.exception;

import one.appscale.relayclientapi.common.exception.AbstractServerException;

import java.io.Serial;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class AbstractS3Exception extends AbstractServerException {
    @Serial
    private static final long serialVersionUID = 850639729373669821L;

    public AbstractS3Exception(final String key, final Throwable e) {
        super(INTERNAL_SERVER_ERROR, "s3 object key:" + key, e);
    }
}
