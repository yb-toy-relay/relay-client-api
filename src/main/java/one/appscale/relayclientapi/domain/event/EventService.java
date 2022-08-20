package one.appscale.relayclientapi.domain.event;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.search.ActivityLogSearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getEvents(ActivityLogSearchQuery searchQuery) {
        return eventRepository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                                            searchQuery.startEpocSecond(),
                                                                            searchQuery.endEpocSecond());
    }
}
