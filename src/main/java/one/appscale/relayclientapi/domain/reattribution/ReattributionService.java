package one.appscale.relayclientapi.domain.reattribution;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.search.ActivityLogSearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReattributionService {
    private final ReattributionRepository reattributionRepository;

    public List<Reattribution> getCsvData(final ActivityLogSearchQuery searchQuery) {
        return reattributionRepository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                                searchQuery.startEpocSecond(),
                                                                searchQuery.endEpocSecond());
    }
}
