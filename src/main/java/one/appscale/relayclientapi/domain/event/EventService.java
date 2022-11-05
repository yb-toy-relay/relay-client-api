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
    public boolean hasData(final ActivityLogSearchQuery searchQuery) {
        final long count = repository.countBy(searchQuery.appToken(),
                                              searchQuery.startEpocSecond(),
                                              searchQuery.endEpocSecond());
        return count > 0;
    }

    @Override
    public CsvData getCsvData(final ActivityLogSearchQuery searchQuery) {
        final List<EventCsv> events = repository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                                          searchQuery.startEpocSecond(),
                                                                          searchQuery.endEpocSecond())
                                                .stream()
                                                .map(EventCsv::of)
                                                .toList();
        return new CsvData(searchQuery.activityKind(),
                           searchQuery.zoneId(),
                           searchQuery.appToken(),
                           searchQuery.startDate(),
                           searchQuery.endDate(),
                           events);
    }
}
