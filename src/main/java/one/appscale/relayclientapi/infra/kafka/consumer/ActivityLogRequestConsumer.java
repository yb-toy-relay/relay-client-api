package one.appscale.relayclientapi.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relayclientapi.domain.csv.CsvResource;
import one.appscale.relayclientapi.domain.csv.CsvService;
import one.appscale.relayclientapi.infra.kafka.producer.ActivityLogRequestProducer;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivityLogRequestConsumer {
    private final CsvService csvService;

    @KafkaListener(topics = ActivityLogRequestProducer.TOPIC,
                   containerFactory = "activityLogRequestConsumerFactory")
    public void consumeActivityLogCsvRequest(final ActivityLogCsvRequest request) {
        log.info("consume ActivityLogCsvRequest: {}", request);
        final CsvResource csvResource = csvService.getCsvResource(ActivityLogSearchQuery.of(request));
    }
}
