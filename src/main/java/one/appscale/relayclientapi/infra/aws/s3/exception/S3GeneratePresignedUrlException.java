package one.appscale.relayclientapi.infra.aws.s3.exception;

import java.io.Serial;

public class S3GeneratePresignedUrlException extends AbstractS3Exception {
    @Serial
    private static final long serialVersionUID = -1619585168474653637L;

    public S3GeneratePresignedUrlException(String key, Throwable e) {
        super(key, e);
    }
}
