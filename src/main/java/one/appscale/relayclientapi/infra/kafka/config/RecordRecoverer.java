package one.appscale.relayclientapi.infra.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;

@Slf4j
public class RecordRecoverer {
    public static ConsumerRecordRecoverer logAndSkipRecoverer() {
        return (consumerRecord, exception) -> log.error("Retry failed on record:{}", consumerRecord, exception);
    }
}
