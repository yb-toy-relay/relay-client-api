package one.appscale.relayclientapi.infra.persistence;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.install.Install;
import one.appscale.relayclientapi.domain.install.InstallRepository;
import one.appscale.relayclientapi.infra.persistence.mongo.InstallDocument;
import one.appscale.relayclientapi.infra.persistence.mongo.InstallMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstallRepositoryImpl implements InstallRepository {
    private final InstallMongoRepository installMongoRepository;

    @Override
    public List<Install> findAllByAppTokenAndDate(String appToken,
                                                  long startTimeEpocSecond,
                                                  long endTimeEpocSecond) {
        return installMongoRepository.findAllByAppTokenAndDate(appToken, startTimeEpocSecond, endTimeEpocSecond)
                                     .stream()
                                     .map(InstallDocument::toInstall)
                                     .toList();
    }
}
