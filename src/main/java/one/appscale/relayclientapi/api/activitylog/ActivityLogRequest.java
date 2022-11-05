package one.appscale.relayclientapi.api.activitylog;

import com.fasterxml.jackson.annotation.JsonFormat;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relaycommon.ActivityKind;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ActivityLogRequest(@NotEmpty String activityKind,
                                 @NotEmpty String apiKey,
                                 @NotEmpty String appToken,
                                 @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                 @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                 String timezone,
                                 @NotEmpty @Email String email) {
    public ActivityLogCsvRequest toActivityLogCsvRequest(final String traceId) {
        return ActivityLogCsvRequest.newBuilder()
                                    .setActivityKind(ActivityKind.fromValue(activityKind).value())
                                    .setApiKey(apiKey)
                                    .setAppToken(appToken)
                                    .setStartDate(startDate.toString())
                                    .setEndDate(endDate.toString())
                                    .setZoneId(timezone)
                                    .setEmail(email)
                                    .setTraceId(traceId)
                                    .build();
    }

    public ActivityLogSearchQuery toActivityLogSearchQuery(final String traceId) {
        final ActivityLogCsvRequest activityLogCsvRequest = this.toActivityLogCsvRequest(traceId);
        return ActivityLogSearchQuery.of(activityLogCsvRequest);
    }
}
