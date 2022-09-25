package one.appscale.relayclientapi.api._validator.activitylogrequest;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api._validator.ConstraintViolationHelper;
import one.appscale.relayclientapi.api.activitylog.ActivityLogRequest;
import one.appscale.relaycommon.ActivityKind;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
public class ActivityLogRequestValidator implements ConstraintValidator<ActivityLogRequestConstraint, ActivityLogRequest> {
    @Value("${app.constraints.max-activity-log-request-range}")
    private String MAX_RANGE;

    private final ConstraintViolationHelper helper;

    @Override
    public boolean isValid(ActivityLogRequest request, ConstraintValidatorContext context) {
        if (!isValidActivityKind(request.activityKind())) {
            helper.addConstraintMessage("not a valid activity kind: " + request.activityKind(), context);
            return false;
        }
        if (!isValidDateRange(request.startDate(), request.endDate())) {
            helper.addConstraintDateRange(request.startDate(), request.endDate(), parseInt(MAX_RANGE), context);
            return false;
        }
        if (!isValidTimeZone(request.timezone())) {
            helper.addConstraintMessage("not a valid timezone: " + request.timezone(), context);
            return false;
        }
        return true;
    }

    private boolean isValidActivityKind(final String activityKind) {
        try {
            ActivityKind.fromValue(activityKind);
            return true;
        } catch (final IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidDateRange(final LocalDate startDate, final LocalDate endDate) {
        final LocalDate max = startDate.plusDays(parseInt(MAX_RANGE));
        return endDate.isBefore(max);
    }

    private boolean isValidTimeZone(final String timezone) {
        if (timezone == null) {
            return true;
        }
        try {
            final ZoneId zoneId = ZoneId.of(timezone);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
