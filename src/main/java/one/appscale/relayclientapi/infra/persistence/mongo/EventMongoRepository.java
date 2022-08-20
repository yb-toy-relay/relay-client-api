package one.appscale.relayclientapi.infra.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventMongoRepository extends MongoRepository<EventDocument, String> {
    @Query(value = """
        {
            'appInfo.appToken' : ?0,
            'createdAt' : { $gte: ?1, $lt: ?2 }
        }
        """)
    List<EventDocument> findAllByAppTokenAndDate(String appToken, long startTimeEpocSecond, long endTimeEpocSecond);
}
