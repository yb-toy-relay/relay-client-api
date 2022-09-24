package one.appscale.relayclientapi.api._validator.activitykind;

import one.appscale.relaycommon.ActivityKind;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ActivityKindValidator implements ConstraintValidator<ValidActivityKind, String> {
    @Override
    public boolean isValid(String activityKind, ConstraintValidatorContext context) {
        try {
            ActivityKind.fromValue(activityKind);
            return true;
        } catch (final IllegalArgumentException e) {
            return false;
        }
    }
}
