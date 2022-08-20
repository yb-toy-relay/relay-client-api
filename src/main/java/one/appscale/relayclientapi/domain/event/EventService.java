package one.appscale.relayclientapi.domain.event;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogProvider;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relayclientapi.domain.csv.CsvData;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService implements ActivityLogProvider {
    private final EventMongoRepository repository;

    @Override
    public CsvData getCsvData(ActivityLogSearchQuery searchQuery) {
        final List<EventCsv> events = repository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                                          searchQuery.startEpocSecond(),
                                                                          searchQuery.endEpocSecond())
                                                .stream()
                                                .map(EventCsv::of)
                                                .toList();
        return new CsvData(searchQuery.activityKind(),
                           searchQuery.zoneId(),
                           events);
    }
}
