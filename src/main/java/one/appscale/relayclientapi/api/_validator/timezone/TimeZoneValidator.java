package one.appscale.relayclientapi.api._validator.timezone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.ZoneId;

public class TimeZoneValidator implements ConstraintValidator<ValidTimeZone, String> {
    @Override
    public boolean isValid(String timezone, ConstraintValidatorContext context) {
        if (timezone == null) {
            return true;
        }
        try {
            final ZoneId zoneId = ZoneId.of(timezone);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
