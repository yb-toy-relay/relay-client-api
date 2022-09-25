package one.appscale.relayclientapi.infra.aws.s3.exception;

import java.io.Serial;

public class S3GetObjectMetadataException extends AbstractS3Exception {
    @Serial
    private static final long serialVersionUID = 5101597588273195314L;

    public S3GetObjectMetadataException(String key, Throwable e) {
        super(key, e);
    }
}
