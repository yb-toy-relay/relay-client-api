package one.appscale.relayclientapi.infra.aws.s3.helper;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.util.Map;

public class ObjectMetadataFactory {
    public static ObjectMetadata csvNotifiableObjectMetadata(final String mailTo) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("text/csv");
        objectMetadata.setUserMetadata(Map.of("mailTo", mailTo));
        return objectMetadata;
    }
}
