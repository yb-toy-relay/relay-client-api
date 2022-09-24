package one.appscale.relayclientapi.domain.activitylog;

import one.appscale.relayclientapi.common.support.DateTimeSupport;
import one.appscale.relaycommon.ActivityKind;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;

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

    public static ActivityLogSearchQuery of(final ActivityLogCsvRequest request) {
        final LocalDate startDate = LocalDate.parse(request.getStartDate());
        final LocalDate endDate = LocalDate.parse(request.getEndDate());
        final String zoneId = DateTimeSupport.getZoneIdOrDefault(request.getZoneId());
        return new ActivityLogSearchQuery(ActivityKind.fromValue(request.getActivityKind()),
                                          request.getAppToken(),
                                          startDate,
                                          endDate,
                                          zoneId);
    }
}
