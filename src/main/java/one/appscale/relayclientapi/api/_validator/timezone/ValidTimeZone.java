package one.appscale.relayclientapi.api._validator.timezone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = TimeZoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeZone {
    String message() default "not a valid timezone.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
