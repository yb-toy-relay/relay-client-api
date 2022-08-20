package one.appscale.relayclientapi.infra.persistence;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.reattribution.Reattribution;
import one.appscale.relayclientapi.domain.reattribution.ReattributionRepository;
import one.appscale.relayclientapi.infra.persistence.mongo.ReattributionDocument;
import one.appscale.relayclientapi.infra.persistence.mongo.ReattributionMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReattributionRepositoryImpl implements ReattributionRepository {
    private final ReattributionMongoRepository reattributionMongoRepository;

    @Override
    public List<Reattribution> findAllByAppTokenAndDate(String appToken,
                                                        long startTimeEpocSecond,
                                                        long endTimeEpocSecond) {
        return reattributionMongoRepository.findAllByAppTokenAndDate(appToken, startTimeEpocSecond, endTimeEpocSecond)
                                           .stream()
                                           .map(ReattributionDocument::toReattribution)
                                           .toList();
    }
}
