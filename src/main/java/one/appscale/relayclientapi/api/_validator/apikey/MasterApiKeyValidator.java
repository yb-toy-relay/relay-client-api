package one.appscale.relayclientapi.api._validator.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api._validator.ConstraintViolationHelper;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class MasterApiKeyValidator implements ConstraintValidator<HasMasterApiKey, String> {
    @Value("${app.api-key.master}")
    private String master;

    private final ConstraintViolationHelper helper;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (master.equals(value)) {
            return true;
        }

        helper.addConstraintMessage("not a valid master key", context);
        return false;
    }
}
