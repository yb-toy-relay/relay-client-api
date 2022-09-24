package one.appscale.relayclientapi.infra.aws.s3.exception;

import one.appscale.relayclientapi.common.exception.AbstractServerException;

import java.io.Serial;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class S3PutObjectException extends AbstractServerException {
    @Serial
    private static final long serialVersionUID = 6009519018638041631L;

    public S3PutObjectException(final String key, final Throwable e) {
        super(INTERNAL_SERVER_ERROR, "s3 put object error. key:" + key, e);
    }
}
