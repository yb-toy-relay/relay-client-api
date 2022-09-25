package one.appscale.relayclientapi.domain.csv;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.domain.notification.RelayMailSender;
import one.appscale.relayclientapi.infra.aws.s3.RelayS3Client;
import org.springframework.stereotype.Component;

import java.net.URL;

import static one.appscale.relayclientapi.infra.aws.s3.UserMetadata.Keys.MAIL_TO;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvNotificationService {
    private final RelayS3Client s3Client;
    private final RelayMailSender mailSender;

    public String getEmailFromS3Object(final String objectKey) {
        final ObjectMetadata objectMetadata = s3Client.getObjectMetadata(objectKey);
        if (objectMetadata == null) {
            log.warn("objectMetadata is null. key:{}", objectKey);
            return "";
        }
        if (objectMetadata.getUserMetadata() == null) {
            log.warn("objectMetadata.userMetadata is null. key:{}", objectKey);
            return "";
        }
        return objectMetadata.getUserMetadata().get(MAIL_TO);
    }

    public URL generatePresignedUrl(final String objectKey) {
        return s3Client.generatePresignedURL(objectKey);
    }

    public void sendPresignedUrl(final String mailTo, URL presignedUrl) {
        mailSender.sendMessage(mailTo, "[appscale] activity kind csv", presignedUrl.toString());
    }
}
