package one.appscale.relayclientapi.domain.activitylog;

import one.appscale.relayclientapi.domain.csv.CsvData;

@FunctionalInterface
public interface ActivityLogProvider {
    CsvData getCsvData(ActivityLogSearchQuery searchQuery);
}
