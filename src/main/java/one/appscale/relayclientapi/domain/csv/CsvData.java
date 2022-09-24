package one.appscale.relayclientapi.domain.csv;

import one.appscale.relayclientapi.common.support.DateTimeSupport;
import one.appscale.relayclientapi.domain.csv.exception.CsvNoDataException;
import one.appscale.relaycommon.ActivityKind;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.List;

import static one.appscale.relayclientapi.common.support.DateTimeSupport.localDateToString;

public record CsvData(ActivityKind activityKind,
                      String zoneId,
                      String appToken,
                      LocalDate startDate,
                      LocalDate endDate,
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

    // appToken_activityKind_startDate_endDate_timeZone_randomKey.csv
    public String getFileName() {
        int length = 5;
        final String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(length);
        return String.format("%s_%s_%s_%s_%s_%s.csv",
                             appToken,
                             activityKind,
                             localDateToString(startDate),
                             localDateToString(endDate),
                             zoneId(),
                             randomAlphanumeric);
    }

    public CsvMetadata getCsvMetadata() {
        return new CsvMetadata(appToken,
                               activityKind.value(),
                               localDateToString(startDate),
                               localDateToString(endDate),
                               DateTimeSupport.getZoneIdOrDefault(zoneId));
    }
}
