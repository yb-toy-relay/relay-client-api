package one.appscale.relayclientapi.infra.aws.s3;

import java.util.Map;

public record UserMetadata(String email,
                           String activityKind,
                           String appToken,
                           String startDate,
                           String endDate,
                           String zoneId) {
    public static class Keys {
        public static final String MAIL_TO = "mailTo";
        public static final String ACTIVITY_KIND = "activityKind";
        public static final String APP_TOKEN = "appToken";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";
        public static final String TIME_ZONE = "timezone";
    }

    public Map<String, String> toMap() {
        return Map.of(
            Keys.MAIL_TO, email,
            Keys.ACTIVITY_KIND, activityKind,
            Keys.APP_TOKEN, appToken,
            Keys.START_DATE, startDate,
            Keys.END_DATE, endDate,
            Keys.TIME_ZONE, zoneId
        );
    }
}
