package one.appscale.relayclientapi.infra.aws.s3.exception;

import java.io.Serial;

public class S3PutObjectException extends AbstractS3Exception {
    @Serial
    private static final long serialVersionUID = 6009519018638041631L;

    public S3PutObjectException(String key, Throwable e) {
        super(key, e);
    }
}
