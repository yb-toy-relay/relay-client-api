package one.appscale.relayclientapi.domain.reattribution;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogProvider;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relayclientapi.domain.csv.CsvData;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReattributionService implements ActivityLogProvider {
    private final ReattributionMongoRepository repository;

    public CsvData getCsvData(final ActivityLogSearchQuery searchQuery) {
        final List<ReattributionCsv> reattributions = repository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                                                                       searchQuery.startEpocSecond(),
                                                                                                       searchQuery.endEpocSecond())
                                                                             .stream()
                                                                             .map(ReattributionCsv::of)
                                                                             .toList();
        return new CsvData(searchQuery.activityKind(),
                           searchQuery.zoneId(),
                           reattributions);
    }
}
