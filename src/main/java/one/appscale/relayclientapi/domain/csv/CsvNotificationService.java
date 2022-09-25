package one.appscale.relayclientapi.domain.csv;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.domain.notification.RelayMailSender;
import one.appscale.relayclientapi.infra.aws.s3.RelayS3Client;
import one.appscale.relayclientapi.infra.aws.s3.UserMetadata;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvNotificationService {
    private final RelayS3Client s3Client;
    private final RelayMailSender mailSender;

    public UserMetadata getUserMetadataFromS3Object(final String objectKey) {
        final ObjectMetadata objectMetadata = s3Client.getObjectMetadata(objectKey);
        if (objectMetadata == null) {
            log.warn("objectMetadata is null. key:{}", objectKey);
            return UserMetadata.EMPTY;
        }
        if (objectMetadata.getUserMetadata() == null) {
            log.warn("objectMetadata.userMetadata is null. key:{}", objectKey);
            return UserMetadata.EMPTY;
        }
        final Map<String, String> userMetadataMap = objectMetadata.getUserMetadata();
        return UserMetadata.of(userMetadataMap);
    }

    public URL generatePresignedUrl(final String objectKey) {
        return s3Client.generatePresignedURL(objectKey);
    }

    public void sendPresignedUrl(final UserMetadata userMetadata, URL presignedUrl) {
        final Map<String, String> variables = Map.of(
            "activityKind", userMetadata.activityKind(),
            "appToken", userMetadata.appToken(),
            "startDate", userMetadata.startDate(),
            "endDate", userMetadata.endDate(),
            "timezone", userMetadata.zoneId(),
            "presignedUrl", presignedUrl.toString()
        );
        final String title = """
            [AppScale] Raw Data: {%s}_{%s}_{%s}-{%s}
            """.formatted(userMetadata.appToken(),
                          userMetadata.activityKind(),
                          userMetadata.startDate(),
                          userMetadata.endDate());
        mailSender.sendMimeMessage(userMetadata.email(),
                                   title,
                                   "email-template",
                                   variables);
    }
}
