package one.appscale.relayclientapi.common.support;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class DateTimeSupport {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'.'SSS";
    public static final String DATETIME_FORMAT_CSV_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final ZoneId ZONE_ID_SEOUL = ZoneId.of("Asia/Seoul");
    public static final DateTimeFormatter CSV_FILENAME_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static long localDateToEpochSecond(final LocalDate localDate, String zoneId) {
        return localDate.atStartOfDay(ZoneId.of(zoneId)).toEpochSecond();
    }

    public static String localDateToString(final LocalDate localDate) {
        return localDate.format(CSV_FILENAME_DATE_FORMATTER);
    }

    public static String epochSecondToLocalDateTimeString(final Long epochSecond, final String zoneId) {
        if (epochSecond == null) {
            return "";
        }
        final Instant instant = Instant.ofEpochSecond(epochSecond);
        final LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.of(zoneId));
        return ldt.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_CSV_DEFAULT));
    }

    public static String getZoneIdOrDefault(final String zoneId) {
        final ZoneId validZoneId = ZoneId.of(zoneId);
        return isBlank(zoneId) ? "UTC" : validZoneId.toString();
    }
}
