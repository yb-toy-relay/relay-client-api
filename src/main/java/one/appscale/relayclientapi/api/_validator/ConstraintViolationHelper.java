package one.appscale.relayclientapi.api._validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
public class ConstraintViolationHelper {
    public void addConstraintMessage(final String message,
                                     final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }

    public void addConstraintDateRange(final LocalDate start,
                                       final LocalDate end,
                                       final int max,
                                       final ConstraintValidatorContext context) {
        final String message = String.format("max range is %s. but start:%s, end:%s", max, start, end);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
}
