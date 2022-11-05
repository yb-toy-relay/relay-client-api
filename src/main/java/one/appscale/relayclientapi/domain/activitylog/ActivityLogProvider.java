package one.appscale.relayclientapi.domain.activitylog;

import one.appscale.relayclientapi.domain.csv.CsvData;

public interface ActivityLogProvider {
    boolean hasData(ActivityLogSearchQuery searchQuery);
    CsvData getCsvData(ActivityLogSearchQuery searchQuery);
}
