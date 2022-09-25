package one.appscale.relayclientapi.infra.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.infra.aws.s3.exception.S3GetObjectMetadataException;
import one.appscale.relayclientapi.infra.aws.s3.exception.S3PutObjectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RelayS3Client {
    @Value("${aws.s3.bucket}")
    public String bucket;

    private final AmazonS3 s3Client;

    public void putObject(final S3File s3File, final ObjectMetadata objectMetadata) {
        final PutObjectRequest putObjectRequest = s3File.toPutObjectRequest(bucket, objectMetadata);
        try {
            s3Client.putObject(putObjectRequest);
            log.info("put object complete. key:{}", putObjectRequest.getKey());
        } catch (RuntimeException e) {
            throw new S3PutObjectException(putObjectRequest.getKey(), e);
        }
    }

    public ObjectMetadata getObjectMetadata(final String key) {
        try {
            return s3Client.getObjectMetadata(bucket, key);
        } catch (RuntimeException e) {
            throw new S3GetObjectMetadataException(key, e);
        }
    }
}
