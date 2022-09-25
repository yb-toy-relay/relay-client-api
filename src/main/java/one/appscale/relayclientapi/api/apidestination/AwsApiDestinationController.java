package one.appscale.relayclientapi.api.apidestination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.common.exception.ApiUnauthorizedException;
import one.appscale.relayclientapi.domain.csv.CsvNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URL;

@Slf4j
@Validated
@RestController
@RequestMapping("/api-destination")
@RequiredArgsConstructor
public class AwsApiDestinationController {
    private static final String HEADER_RELAY_CLIENT_API_KEY = "Relay-Client-API-Key";

    @Value("${app.api-key.master}")
    private String master;

    private final CsvNotificationService s3NotificationService;

    @PostMapping("/s3/object/created")
    public String receiveS3ObjectCreatedEvent(
        @RequestHeader(HEADER_RELAY_CLIENT_API_KEY) String apiKey,
        @Valid @RequestBody EventBridgeS3ObjectCreated event) {
        if (!this.master.equals(apiKey)) {
            throw new ApiUnauthorizedException("Invalid api key");
        }
        final String objectKey = event.detail().object().key();
        final String notificationEmail = s3NotificationService.getEmailFromS3Object(objectKey);
        final URL url = s3NotificationService.generatePresignedUrl(objectKey);
        return notificationEmail;
    }
}
