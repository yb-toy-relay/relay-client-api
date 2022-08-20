package one.appscale.relayclientapi.domain.event;

import java.util.List;

public interface EventRepository {
    List<Event> findAllByAppTokenAndDate(String activityKind, long startDate, long endDate);
}
