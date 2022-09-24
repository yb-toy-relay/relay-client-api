package one.appscale.relayclientapi.infra.aws.s3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum S3KeyPrefix {
    ACTIVITY_LOG_CSV("activity_log/csv");

    private final String prefix;
}
