package one.appscale.relayclientapi.api.activitylog;

import com.fasterxml.jackson.annotation.JsonFormat;
import one.appscale.relaycommon.ActivityKind;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ActivityLogRequest(@NotEmpty String activityKind,
                                 @NotEmpty String apiKey,
                                 @NotEmpty String appToken,
                                 @NotNull @JsonFormat(pattern = "yyyyMMdd") LocalDate startDate,
                                 @NotNull @JsonFormat(pattern = "yyyyMMdd") LocalDate endDate,
                                 String timezone,
                                 @Email String email) {
    public ActivityLogCsvRequest toActivityLogCsvRequest() {
        return ActivityLogCsvRequest.newBuilder()
                                    .setActivityKind(ActivityKind.fromValue(activityKind).value())
                                    .setApiKey(apiKey)
                                    .setAppToken(appToken)
                                    .setStartDate(startDate.toString())
                                    .setEndDate(endDate.toString())
                                    .setZoneId(timezone)
                                    .setEmail(email)
                                    .build();
    }
}
