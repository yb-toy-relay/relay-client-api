package one.appscale.relayclientapi.infra.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivityLogRequestProducer {
    public static final String TOPIC = "request-activity-log-csv-v1";

    private final KafkaTemplate<String, ActivityLogCsvRequest> kafkaTemplate;

    public void sendCsvRequest(final ActivityLogCsvRequest request) {
        kafkaTemplate.send(TOPIC, request.getAppToken(), request);
        log.info("[trace-activity-log][{}] produce. topic:{}", request.getTraceId(), TOPIC);
    }
}
