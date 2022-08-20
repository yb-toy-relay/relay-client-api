package one.appscale.relayclientapi.domain.install;

import java.util.List;

public interface InstallRepository {
    List<Install> findAllByAppTokenAndDate(String activityKind, long startDate, long endDate);
}
