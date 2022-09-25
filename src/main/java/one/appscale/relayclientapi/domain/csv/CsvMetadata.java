package one.appscale.relayclientapi.domain.csv;

import one.appscale.relayclientapi.infra.aws.s3.S3KeyPrefix;
import one.appscale.relayclientapi.infra.aws.s3.UserMetadata;
import org.apache.commons.lang3.RandomStringUtils;

public record CsvMetadata(String appToken,
                          String activityKind,
                          String startDate,
                          String endDate,
                          String zoneId) {
    // appToken_activityKind_startDate_endDate_timeZone_randomKey.csv
    private String getFileName() {
        return String.format("%s_%s_%s_%s_%s_%s.csv",
                             appToken,
                             activityKind,
                             startDate,
                             endDate,
                             getZoneIdForFileName(),
                             getRandomString());
    }

    private String getRandomString() {
        int length = 5;
        final String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(length);
        final long currentTimeMillis = System.currentTimeMillis();
        return String.format("%s_%s", randomAlphanumeric, currentTimeMillis);
    }

    private String getZoneIdForFileName() {
        if (zoneId == null) {
            return "";
        }
        return zoneId.replaceAll("/", "_");
    }

    public String getS3Key() {
        return String.format("%s/%s/%s",
                             S3KeyPrefix.ACTIVITY_LOG_CSV.getPrefix(),
                             appToken,
                             getFileName());
    }

    public UserMetadata toUserMetadata(final String email) {
        return new UserMetadata(email,
                                activityKind,
                                appToken,
                                startDate,
                                endDate,
                                zoneId);
    }
}
