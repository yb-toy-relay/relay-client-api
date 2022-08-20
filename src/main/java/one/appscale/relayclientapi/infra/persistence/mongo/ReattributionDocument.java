package one.appscale.relayclientapi.infra.persistence.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import one.appscale.relayclientapi.domain.reattribution.Reattribution;
import one.appscale.relaydomain.domain.ActivityKey;
import one.appscale.relaydomain.domain.ActivityKind;
import one.appscale.relaydomain.domain.AppInfo;
import one.appscale.relaydomain.domain.ChannelInfo;
import one.appscale.relaydomain.domain.DeviceDetail;
import one.appscale.relaydomain.domain.DeviceIdInfo;
import one.appscale.relaydomain.domain.EngagementInfo;
import one.appscale.relaydomain.domain.EnvironmentInfo;
import one.appscale.relaydomain.domain.FacebookReferrerInfo;
import one.appscale.relaydomain.domain.ReattributionInfo;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PROTECTED;

@ToString
@Getter
@Builder(builderMethodName = "requiredBuilder")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "reattribution")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ReattributionDocument {
    @EqualsAndHashCode.Include
    @Id
    private ObjectId id;
    private long createdAt;
    private String activityKind;
    private AppInfo appInfo;
    private ChannelInfo channelInfo;
    private DeviceIdInfo deviceIdInfo;
    private EngagementInfo engagementInfo;
    private DeviceDetail deviceDetail;
    private ReattributionInfo reattributionInfo;
    private EnvironmentInfo environmentInfo;
    private FacebookReferrerInfo facebookReferrerInfo;

    public static ReattributionDocumentBuilder builder(final ObjectId id,
                                                       final long createdAt,
                                                       final ActivityKind activityKind) {
        return ReattributionDocument.requiredBuilder()
                                    .id(id)
                                    .createdAt(createdAt)
                                    .activityKind(activityKind.value());
    }

    public Reattribution toReattribution() {
        return Reattribution.of(id,
                                new ActivityKey(createdAt, ActivityKind.fromValue(activityKind)),
                                appInfo,
                                channelInfo,
                                engagementInfo,
                                deviceIdInfo,
                                deviceDetail,
                                reattributionInfo,
                                environmentInfo,
                                facebookReferrerInfo);
    }
}
