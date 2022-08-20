package one.appscale.relayclientapi.domain.reattribution;

import java.util.List;

public interface ReattributionRepository {
    List<Reattribution> findAllByAppTokenAndDate(String activityKind, long startDate, long endDate);
}
