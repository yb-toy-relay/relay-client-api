package one.appscale.relayclientapi.common.properties;

import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * The maximum back off time must not exceed the max.poll.interval.ms consumer property,
 * to avoid a rebalance.
 */
public record RetryProperties(@DurationUnit(ChronoUnit.SECONDS) Duration interval, long maxAttempts) {
}
