package one.appscale.relayclientapi.api.apidestination;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public record EventBridgeS3ObjectCreated(String version,
                                         String id,
                                         @JsonProperty("detail-type") String detailType,
                                         String source,
                                         String account,
                                         String time,
                                         String region,
                                         List<String> resources,
                                         Detail detail) {
    public static record Detail(String version,
                                @NotNull BucketInfo bucket,
                                @NotNull ObjectInfo object) {
        public static record BucketInfo(String name) {
        }
        public static record ObjectInfo(String key,
                                        Integer size,
                                        String etag,
                                        @JsonProperty("version-id") String versionId,
                                        String sequencer) {
        }

    }
}
