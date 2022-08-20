package one.appscale.relayclientapi.api.v1.dto;

import one.appscale.relayclientapi.domain.search.ActivityLogSearchQuery;
import one.appscale.relaydomain.domain.ActivityKind;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record CsvDownloadRequest(@NotNull ActivityKind activityKind,
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
