package one.appscale.relayclientapi.infra.mail.type;

import one.appscale.relayclientapi.infra.aws.s3.UserMetadata;

import java.net.URL;
import java.util.Map;

public record ActivityLogMimeMessage(String traceId,
                                     String email,
                                     String activityKind,
                                     String appToken,
                                     String startDate,
                                     String endDate,
                                     String timezone,
                                     String presignedUrl) implements RelayMimeMessage {
    public static ActivityLogMimeMessage of(final UserMetadata userMetadata, final URL presignedUrl) {
        return new ActivityLogMimeMessage(userMetadata.traceId(),
                                          userMetadata.email(),
                                          userMetadata.activityKind(),
                                          userMetadata.appToken(),
                                          userMetadata.startDate(),
                                          userMetadata.endDate(),
                                          userMetadata.zoneId(),
                                          presignedUrl.toString());
    }

    @Override
    public String to() {
        return this.email;
    }

    @Override
    public String templateName() {
        return "email-template-activity-log";
    }

    @Override
    public String title() {
        return """
            [AppScale] Raw Data: %s_%s_%s-%s
            """.formatted(appToken,
                          activityKind,
                          startDate,
                          endDate);
    }

    @Override
    public Map<String, String> variables() {
        return Map.of(
            "traceId", traceId,
            "activityKind", activityKind,
            "appToken", appToken,
            "startDate", startDate,
            "endDate", endDate,
            "timezone", timezone,
            "presignedUrl", presignedUrl
        );
    }
}
