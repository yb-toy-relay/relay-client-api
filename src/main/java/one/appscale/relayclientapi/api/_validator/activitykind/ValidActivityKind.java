package one.appscale.relayclientapi.api._validator.activitykind;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ActivityKindValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidActivityKind {
    String message() default "not a valid activity kind.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
