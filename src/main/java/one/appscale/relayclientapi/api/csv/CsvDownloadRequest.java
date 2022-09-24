package one.appscale.relayclientapi.api.csv;

import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relaycommon.ActivityKind;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Deprecated
public record CsvDownloadRequest(@NotNull ActivityKind activityKind,
                                 @NotEmpty String apiKey,
                                 @NotEmpty String appToken,
                                 @NotNull @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startDate,
                                 @NotNull @DateTimeFormat(pattern = "yyyyMMdd") LocalDate endDate,
                                 String zoneId) {
    public ActivityLogSearchQuery toActivityLogSearchQuery() {
        return new ActivityLogSearchQuery(activityKind,
                                          appToken,
                                          startDate,
                                          endDate,
                                          zoneId);
    }
}
