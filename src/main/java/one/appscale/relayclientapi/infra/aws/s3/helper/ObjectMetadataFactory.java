package one.appscale.relayclientapi.infra.aws.s3.helper;

import com.amazonaws.services.s3.model.ObjectMetadata;
import one.appscale.relayclientapi.domain.csv.CsvMetadata;

import java.util.Map;

public class ObjectMetadataFactory {
    public static ObjectMetadata csvNotifiableObjectMetadata(final String email,
                                                             final CsvMetadata csvMetadata) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("text/csv");
        final Map<String, String> userMetadata = csvMetadata.toUserMetadata(email).toMap();
        objectMetadata.setUserMetadata(userMetadata);
        return objectMetadata;
    }
}
