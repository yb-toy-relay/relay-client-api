package one.appscale.relayclientapi.domain.activitylog;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.event.EventService;
import one.appscale.relayclientapi.domain.install.InstallService;
import one.appscale.relayclientapi.domain.reattribution.ReattributionService;
import one.appscale.relaydomain.domain.ActivityKind;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityLogProviderFactory {
    private final InstallService installService;
    private final EventService eventService;
    private final ReattributionService reattributionService;

    public ActivityLogProvider getProvider(final ActivityKind activityKind) {
        return switch(activityKind) {
            case INSTALL -> installService;
            case EVENT -> eventService;
            case REATTRIBUTION -> reattributionService;
        };
    }
}
