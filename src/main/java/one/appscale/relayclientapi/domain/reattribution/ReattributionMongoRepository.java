package one.appscale.relayclientapi.domain.reattribution;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReattributionMongoRepository extends MongoRepository<ReattributionDocument, String> {
    @Query(value = """
        {
            'appInfo.appToken' : ?0,
            'activityKey.createdAt' : { $gte: ?1, $lt: ?2 }
        }
        """)
    List<ReattributionDocument> findAllByAppTokenAndDate(String appToken, long startTimeEpocSecond, long endTimeEpocSecond);

    @Query(value = """
        {
            'appInfo.appToken' : ?0,
            'activityKey.createdAt' : { $gte: ?1, $lt: ?2 }
        }
        """, count = true)
    long countBy(String appToken, long startTimeEpocSecond, long endTimeEpocSecond);
}
