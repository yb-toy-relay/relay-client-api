package one.appscale.relayclientapi.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("app.consumer.request-activity-log-csv-v1")
public record ActivityLogCsvRequestProperties(ContainerProperties containerConfig,
                                              RetryProperties retry) {
}
