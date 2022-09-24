package one.appscale.relayclientapi.common.properties;

import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public record ContainerProperties(String groupId,
                                  @DurationUnit(ChronoUnit.SECONDS) Duration idleBetweenPolls) {
}
