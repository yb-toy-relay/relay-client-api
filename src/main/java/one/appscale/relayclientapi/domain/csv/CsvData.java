package one.appscale.relayclientapi.domain.csv;

import one.appscale.relayclientapi.common.support.DateTimeSupport;
import one.appscale.relayclientapi.domain.csv.exception.CsvNoDataException;
import one.appscale.relaycommon.ActivityKind;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public record CsvData(ActivityKind activityKind,
                      String zoneId,
                      List<? extends Csv> data) {
    public String zoneId() {
        return DateTimeSupport.getZoneIdOrDefault(zoneId);
    }

    public String[] getHeaders() {
        if (data == null || data.isEmpty()) {
            throw new CsvNoDataException();
        }
        return data.get(0).headers().toArray(String[]::new);
    }

    public List<List<Object>> getBodies() {
        return data.stream().map(csv -> csv.body(zoneId())).toList();
    }

    public String getFileName() {
        int length = 10;
        final String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(length);
        return String.format("%s_%s.csv", activityKind, randomAlphanumeric);
    }
}
