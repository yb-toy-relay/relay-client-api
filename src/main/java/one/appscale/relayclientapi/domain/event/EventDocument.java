package one.appscale.relayclientapi.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import one.appscale.relayschema.domain.ActivityKey;
import one.appscale.relayschema.domain.AppInfo;
import one.appscale.relayschema.domain.ChannelInfo;
import one.appscale.relayschema.domain.DeviceDetail;
import one.appscale.relayschema.domain.DeviceIdInfo;
import one.appscale.relayschema.domain.EnvironmentInfo;
import one.appscale.relayschema.domain.EventInfo;
import one.appscale.relayschema.domain.FacebookReferrerInfo;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PROTECTED;

@ToString
@Getter
@Builder(builderMethodName = "requiredBuilder")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "event")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class EventDocument {
    @EqualsAndHashCode.Include
    @Id
    private ObjectId id;
    private ActivityKey activityKey;
    private AppInfo appInfo;
    private ChannelInfo channelInfo;
    private DeviceIdInfo deviceIdInfo;
    private DeviceDetail deviceDetail;
    private EventInfo eventInfo;
    private EnvironmentInfo environmentInfo;
    private FacebookReferrerInfo facebookReferrerInfo;
}
