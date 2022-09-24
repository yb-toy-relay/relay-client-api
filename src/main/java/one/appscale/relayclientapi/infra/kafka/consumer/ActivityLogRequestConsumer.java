package one.appscale.relayclientapi.infra.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.infra.kafka.producer.ActivityLogRequestProducer;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ActivityLogRequestConsumer {
    @KafkaListener(topics = ActivityLogRequestProducer.TOPIC,
                   containerFactory = "activityLogRequestConsumerFactory")
    public void consumeActivityLogCsvRequest(final ActivityLogCsvRequest request) {
        log.info("consume ActivityLogCsvRequest: {}", request);
    }
}
