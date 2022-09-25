package one.appscale.relayclientapi.infra.aws.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.infra.aws.s3.exception.S3GeneratePresignedUrlException;
import one.appscale.relayclientapi.infra.aws.s3.exception.S3GetObjectMetadataException;
import one.appscale.relayclientapi.infra.aws.s3.exception.S3PutObjectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class RelayS3Client {
    @Value("${aws.s3.bucket}")
    public String bucket;

    @Value("${aws.s3.presigned-url-ttl}")
    public Duration presignedUrlTtl;

    private final AmazonS3 s3Client;

    public void putObject(final S3File s3File, final ObjectMetadata objectMetadata) {
        final PutObjectRequest putObjectRequest = s3File.toPutObjectRequest(bucket, objectMetadata);
        try {
            s3Client.putObject(putObjectRequest);
            log.info("put object complete. key:{}", putObjectRequest.getKey());
        } catch (final RuntimeException e) {
            throw new S3PutObjectException(putObjectRequest.getKey(), e);
        }
    }

    public ObjectMetadata getObjectMetadata(final String key) {
        try {
            return s3Client.getObjectMetadata(bucket, key);
        } catch (final RuntimeException e) {
            throw new S3GetObjectMetadataException(key, e);
        }
    }

    // https://docs.aws.amazon.com/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html
    public URL generatePresignedURL(final String key) {
        final var generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key)
            .withMethod(HttpMethod.GET)
            .withExpiration(initPresignedUrlExpiration());
        try {
            return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        } catch (final RuntimeException e) {
            throw new S3GeneratePresignedUrlException(key, e);
        }
    }

    private Date initPresignedUrlExpiration() {
        final Date expiration = new Date();
        long ttl = Instant.now().toEpochMilli() + presignedUrlTtl.toMillis();
        expiration.setTime(ttl);
        return expiration;
    }
}
