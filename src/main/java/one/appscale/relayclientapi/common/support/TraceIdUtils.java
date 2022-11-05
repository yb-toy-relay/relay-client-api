package one.appscale.relayclientapi.common.support;

import one.appscale.relayschema.request.csv.ActivityLogCsvRequest;

import java.util.UUID;

public class TraceIdUtils {
    public static String create() {
        return UUID.randomUUID().toString();
    }

    public static String get(final ActivityLogCsvRequest request) {
        return request == null ? null : request.getTraceId();
    }
}
