package one.appscale.relayclientapi.domain.csv;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.infra.aws.s3.RelayS3Client;
import org.springframework.stereotype.Component;

import java.net.URL;

import static one.appscale.relayclientapi.infra.aws.s3.helper.ObjectMetadataFactory.UserMetadataKeys.MAIL_TO;

@RequiredArgsConstructor
@Component
public class CsvNotificationService {
    private final RelayS3Client s3Client;

    public String getEmailFromS3Object(final String objectKey) {
        final ObjectMetadata objectMetadata = s3Client.getObjectMetadata(objectKey);
        if (objectMetadata == null) {
            return "";
        }
        if (objectMetadata.getUserMetadata() == null) {
            return "";
        }
        return objectMetadata.getUserMetadata().get(MAIL_TO);
    }

    public URL generatePresignedUrl(final String objectKey) {
        return s3Client.generatePresignedURL(objectKey);
    }
}
