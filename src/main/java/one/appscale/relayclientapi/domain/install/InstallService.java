package one.appscale.relayclientapi.domain.install;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.search.ActivityLogSearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InstallService {
    private final InstallRepository installRepository;

    public List<Install> getInstalls(final ActivityLogSearchQuery searchQuery) {
        return installRepository.findAllByAppTokenAndDate(searchQuery.appToken(),
                                                          searchQuery.startEpocSecond(),
                                                          searchQuery.endEpocSecond());
    }
}
