package one.appscale.relayclientapi.infra.aws.s3.helper;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.util.Map;

public class ObjectMetadataFactory {
    public static class UserMetadataKeys {
        public static final String MAIL_TO = "mailTo";
    }

    public static ObjectMetadata csvNotifiableObjectMetadata(final String mailTo) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("text/csv");
        objectMetadata.setUserMetadata(Map.of(UserMetadataKeys.MAIL_TO, mailTo));
        return objectMetadata;
    }
}
