package one.appscale.relayclientapi.api.apidestination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.common.exception.ApiUnauthorizedException;
import one.appscale.relayclientapi.domain.csv.CsvNotificationService;
import one.appscale.relayclientapi.infra.aws.s3.UserMetadata;
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

    @Value("${aws.api-destination-key}")
    private String awsApiDestinationKey;

    private final CsvNotificationService csvNotificationService;

    @PostMapping("/s3/object/created")
    public void receiveS3ObjectCreatedEvent(
        @RequestHeader(HEADER_RELAY_CLIENT_API_KEY) String apiKey,
        @Valid @RequestBody EventBridgeS3ObjectCreated event) {
        if (!this.awsApiDestinationKey.equals(apiKey)) {
            throw new ApiUnauthorizedException("Invalid api key");
        }
        final String objectKey = event.detail().object().key();
        final UserMetadata userMetadata = csvNotificationService.getUserMetadataFromS3Object(objectKey);
        if (userMetadata.isEmpty()) {
            throw new UserMetadataEmptyException(objectKey);
        }
        final URL url = csvNotificationService.generatePresignedUrl(objectKey);
        csvNotificationService.sendPresignedUrl(userMetadata, url);
    }
}
