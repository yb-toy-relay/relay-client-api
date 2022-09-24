package one.appscale.relayclientapi.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relayclientapi.domain.csv.CsvResource2;
import one.appscale.relayclientapi.domain.csv.CsvService2;
import one.appscale.relayclientapi.infra.aws.s3.RelayS3Client;
import one.appscale.relayclientapi.infra.kafka.producer.ActivityLogRequestProducer;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static one.appscale.relayclientapi.infra.aws.s3.helper.ObjectMetadataFactory.csvNotifiableObjectMetadata;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivityLogRequestConsumer {
    private final CsvService2 csvService;
    private final RelayS3Client relayS3Client;

    @KafkaListener(topics = ActivityLogRequestProducer.TOPIC,
                   containerFactory = "activityLogRequestConsumerFactory")
    public void consumeActivityLogCsvRequest(final ActivityLogCsvRequest request) {
        log.info("consume ActivityLogCsvRequest: {}", request);
        final CsvResource2 csvResource = csvService.getCsvResource(ActivityLogSearchQuery.of(request));
        relayS3Client.putObject(csvResource, csvNotifiableObjectMetadata(request.getEmail()));
    }
}
