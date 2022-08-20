package one.appscale.relayclientapi.infra.persistence.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import one.appscale.relayclientapi.domain.install.Install;
import one.appscale.relaydomain.domain.ActivityKey;
import one.appscale.relaydomain.domain.ActivityKind;
import one.appscale.relaydomain.domain.AppInfo;
import one.appscale.relaydomain.domain.AttributionInfo;
import one.appscale.relaydomain.domain.ChannelInfo;
import one.appscale.relaydomain.domain.DeviceDetail;
import one.appscale.relaydomain.domain.DeviceIdInfo;
import one.appscale.relaydomain.domain.EngagementInfo;
import one.appscale.relaydomain.domain.EnvironmentInfo;
import one.appscale.relaydomain.domain.FacebookReferrerInfo;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PROTECTED;

@ToString
@Getter
@Builder(builderMethodName = "requiredBuilder")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "install")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class InstallDocument {
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
    private AttributionInfo attributionInfo;
    private EnvironmentInfo environmentInfo;
    private FacebookReferrerInfo facebookReferrerInfo;

    public static InstallDocumentBuilder builder(final ObjectId id,
                                                 final long createdAt,
                                                 final ActivityKind activityKind) {
        return InstallDocument.requiredBuilder()
                              .id(id)
                              .createdAt(createdAt)
                              .activityKind(activityKind.value());
    }

    public Install toInstall() {
        return Install.of(id,
                          new ActivityKey(createdAt, ActivityKind.fromValue(activityKind)),
                          appInfo,
                          channelInfo,
                          attributionInfo,
                          engagementInfo,
                          deviceIdInfo,
                          deviceDetail,
                          environmentInfo,
                          facebookReferrerInfo);
    }
}
