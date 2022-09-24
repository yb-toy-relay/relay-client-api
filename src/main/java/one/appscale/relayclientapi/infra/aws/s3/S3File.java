package one.appscale.relayclientapi.infra.aws.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public interface S3File {
    PutObjectRequest toPutObjectRequest(String bucket, ObjectMetadata objectMetadata);
}
