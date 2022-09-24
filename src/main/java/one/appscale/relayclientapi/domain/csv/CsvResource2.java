package one.appscale.relayclientapi.domain.csv;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import one.appscale.relayclientapi.infra.aws.s3.S3File;

import java.io.InputStream;

public record CsvResource2(InputStream inputStream,
                           CsvMetadata csvMetadata) implements S3File {
    @Override
    public PutObjectRequest toPutObjectRequest(final String bucket, final ObjectMetadata objectMetadata) {
        return new PutObjectRequest(bucket,
                                    csvMetadata.getS3Key(),
                                    inputStream,
                                    objectMetadata);
    }
}
