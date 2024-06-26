package one.appscale.relayclientapi.domain.install;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogProvider;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relayclientapi.domain.csv.CsvData;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InstallService implements ActivityLogProvider {
    private final InstallMongoRepository repository;

    @Override
    public boolean hasData(final ActivityLogSearchQuery searchQuery) {
        final long count = repository.countBy(searchQuery.appToken(),
                                              searchQuery.startEpocSecond(),
                                              searchQuery.endEpocSecond());
        return count > 0;
    }

    @Override
    public CsvData getCsvData(ActivityLogSearchQuery searchQuery) {
        final List<InstallCsv> installs = repository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                                              searchQuery.startEpocSecond(),
                                                                              searchQuery.endEpocSecond())
                                                    .stream()
                                                    .map(InstallCsv::of)
                                                    .toList();
        return new CsvData(searchQuery.activityKind(),
                           searchQuery.zoneId(),
                           searchQuery.appToken(),
                           searchQuery.startDate(),
                           searchQuery.endDate(),
                           installs);
    }
}
