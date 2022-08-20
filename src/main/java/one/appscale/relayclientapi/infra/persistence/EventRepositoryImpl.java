package one.appscale.relayclientapi.infra.persistence;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.event.Event;
import one.appscale.relayclientapi.domain.event.EventRepository;
import one.appscale.relayclientapi.infra.persistence.mongo.EventDocument;
import one.appscale.relayclientapi.infra.persistence.mongo.EventMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private final EventMongoRepository eventMongoRepository;

    @Override
    public List<Event> findAllByAppTokenAndDate(String appToken,
                                                long startTimeEpocSecond,
                                                long endTimeEpocSecond) {
        return eventMongoRepository.findAllByAppTokenAndDate(appToken, startTimeEpocSecond, endTimeEpocSecond)
                                   .stream()
                                   .map(EventDocument::toEvent)
                                   .toList();
    }
}
