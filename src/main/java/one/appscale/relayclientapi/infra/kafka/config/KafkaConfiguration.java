package one.appscale.relayclientapi.infra.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.common.properties.ActivityLogCsvRequestProperties;
import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.ExponentialBackOff;

import static one.appscale.relayclientapi.infra.kafka.config.RecordRecoverer.logAndSkipRecoverer;

@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {
    private final ConsumerFactory<String, ActivityLogCsvRequest> consumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ActivityLogCsvRequest> activityLogRequestConsumerFactory(
        final ActivityLogCsvRequestProperties properties) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ActivityLogCsvRequest>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.setBatchListener(false);

        var containerConfig = properties.containerConfig();
        factory.getContainerProperties().setIdleBetweenPolls(containerConfig.idleBetweenPolls().toMillis());
        factory.getContainerProperties().setGroupId(containerConfig.groupId());
        return factory;
    }

    /**
     * The maximum back off time must not exceed the max.poll.interval.ms consumer property,
     * to avoid a rebalance.
     */
    public static final int MAXIMUM_RETRY_TIME_MILLS = 180_000; // 3분

    @Bean
    public DefaultErrorHandler defaultErrorHandler() {
        final var backOff = new ExponentialBackOff();
        backOff.setMaxElapsedTime(MAXIMUM_RETRY_TIME_MILLS);
        var defaultErrorHandler = new DefaultErrorHandler(logAndSkipRecoverer(), backOff);
        defaultErrorHandler.setLogLevel(KafkaException.Level.WARN);
        return defaultErrorHandler;
    }
}
