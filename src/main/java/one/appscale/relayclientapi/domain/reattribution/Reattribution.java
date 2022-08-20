package one.appscale.relayclientapi.domain.reattribution;

import one.appscale.relaydomain.domain.ActivityKey;
import one.appscale.relaydomain.domain.AppInfo;
import one.appscale.relaydomain.domain.ChannelInfo;
import one.appscale.relaydomain.domain.DeviceDetail;
import one.appscale.relaydomain.domain.DeviceIdInfo;
import one.appscale.relaydomain.domain.EngagementInfo;
import one.appscale.relaydomain.domain.EnvironmentInfo;
import one.appscale.relaydomain.domain.FacebookReferrerInfo;
import one.appscale.relaydomain.domain.ReattributionInfo;
import org.bson.types.ObjectId;

public record Reattribution(ObjectId id,
                            ActivityKey activityKey,
                            AppInfo appInfo,
                            ChannelInfo channelInfo,
                            DeviceIdInfo deviceIdInfo,
                            EngagementInfo engagementInfo,
                            DeviceDetail deviceDetail,
                            ReattributionInfo reattributionInfo,
                            EnvironmentInfo environmentInfo,
                            FacebookReferrerInfo facebookReferrerInfo) {
    public static Reattribution of(final ObjectId id,
                                   final ActivityKey activityKey,
                                   final AppInfo appInfo,
                                   final ChannelInfo channelInfo,
                                   final EngagementInfo engagementInfo,
                                   final DeviceIdInfo deviceIdInfo,
                                   final DeviceDetail deviceDetail,
                                   final ReattributionInfo reattributionInfo,
                                   final EnvironmentInfo environmentInfo,
                                   final FacebookReferrerInfo facebookReferrerInfo) {
        return new Reattribution(id,
                                 activityKey,
                                 appInfo,
                                 channelInfo,
                                 deviceIdInfo,
                                 engagementInfo,
                                 deviceDetail,
                                 reattributionInfo,
                                 environmentInfo,
                                 facebookReferrerInfo);
    }
}
