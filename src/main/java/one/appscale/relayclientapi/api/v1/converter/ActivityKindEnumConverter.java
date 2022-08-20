package one.appscale.relayclientapi.api.v1.converter;

import one.appscale.relaycommon.ActivityKind;
import org.springframework.core.convert.converter.Converter;

public class ActivityKindEnumConverter implements Converter<String, ActivityKind> {
    @Override
    public ActivityKind convert(final String source) {
        return ActivityKind.fromValue(source);
    }
}
