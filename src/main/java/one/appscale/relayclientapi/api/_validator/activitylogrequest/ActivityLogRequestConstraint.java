package one.appscale.relayclientapi.api._validator.activitylogrequest;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ActivityLogRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityLogRequestConstraint {
    String message() default "not a valid request.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
