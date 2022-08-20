package one.appscale.relayclientapi.domain.activitylog;

import one.appscale.relayclientapi.common.support.DateTimeSupport;
import one.appscale.relaydomain.domain.ActivityKind;

import java.time.LocalDate;

public record ActivityLogSearchQuery(ActivityKind activityKind,
                                     String appToken,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     String zoneId) {
    public long startEpocSecond() {
        return DateTimeSupport.localDateToEpochSecond(startDate, zoneId());
    }

    public long endEpocSecond() {
        return DateTimeSupport.localDateToEpochSecond(endDate.plusDays(1), zoneId());
    }

    public String zoneId() {
        return DateTimeSupport.getZoneIdOrDefault(zoneId);
    }
}
