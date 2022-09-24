package one.appscale.relayclientapi.infra.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivityLogRequestProducer {
    private static final String topic = "request-activity-log-csv-v1";

    private final KafkaTemplate<Long, ActivityLogCsvRequest> kafkaTemplate;

    public void sendCsvRequest(final ActivityLogCsvRequest activityLogCsvRequest) {
        kafkaTemplate.send(topic, activityLogCsvRequest);
        log.info("csv request sent. topic:{}, data:{}", topic, activityLogCsvRequest);
    }
}
