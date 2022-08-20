package one.appscale.relayclientapi.domain.install;

import one.appscale.relaydomain.domain.ActivityKey;
import one.appscale.relaydomain.domain.AppInfo;
import one.appscale.relaydomain.domain.AttributionInfo;
import one.appscale.relaydomain.domain.ChannelInfo;
import one.appscale.relaydomain.domain.DeviceDetail;
import one.appscale.relaydomain.domain.DeviceIdInfo;
import one.appscale.relaydomain.domain.EngagementInfo;
import one.appscale.relaydomain.domain.EnvironmentInfo;
import one.appscale.relaydomain.domain.FacebookReferrerInfo;
import org.bson.types.ObjectId;

public record Install(ObjectId id,
                      ActivityKey activityKey,
                      AppInfo appInfo,
                      ChannelInfo channelInfo,
                      AttributionInfo attributionInfo,
                      EngagementInfo engagementInfo,
                      DeviceIdInfo deviceIdInfo,
                      DeviceDetail deviceDetail,
                      EnvironmentInfo environmentInfo,
                      FacebookReferrerInfo facebookReferrerInfo) {
    public static Install of(final ObjectId id,
                             final ActivityKey activityKey,
                             final AppInfo appInfo,
                             final ChannelInfo channelInfo,
                             final AttributionInfo attributionInfo,
                             final EngagementInfo engagementInfo,
                             final DeviceIdInfo deviceIdInfo,
                             final DeviceDetail deviceDetail,
                             final EnvironmentInfo environmentInfo,
                             final FacebookReferrerInfo facebookReferrerInfo) {
        return new Install(id,
                           activityKey,
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
