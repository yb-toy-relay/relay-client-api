package one.appscale.relayclientapi.domain.event;

import one.appscale.relaydomain.domain.ActivityKey;
import one.appscale.relaydomain.domain.AppInfo;
import one.appscale.relaydomain.domain.ChannelInfo;
import one.appscale.relaydomain.domain.DeviceDetail;
import one.appscale.relaydomain.domain.DeviceIdInfo;
import one.appscale.relaydomain.domain.EnvironmentInfo;
import one.appscale.relaydomain.domain.EventInfo;
import one.appscale.relaydomain.domain.FacebookReferrerInfo;
import org.bson.types.ObjectId;

public record Event(ObjectId id,
                    ActivityKey activityKey,
                    AppInfo appInfo,
                    ChannelInfo channelInfo,
                    DeviceIdInfo deviceIdInfo,
                    DeviceDetail deviceDetail,
                    EventInfo eventInfo,
                    EnvironmentInfo environmentInfo,
                    FacebookReferrerInfo facebookReferrerInfo) {
    public static Event of(final ActivityKey activityKey,
                           final AppInfo appInfo,
                           final ChannelInfo channelInfo,
                           final DeviceIdInfo deviceIdInfo,
                           final DeviceDetail deviceDetail,
                           final EventInfo eventInfo,
                           final EnvironmentInfo environmentInfo,
                           final FacebookReferrerInfo facebookReferrerInfo) {
        return Event.of(new ObjectId(),
                          activityKey,
                          appInfo,
                          channelInfo,
                          deviceIdInfo,
                          deviceDetail,
                          eventInfo,
                          environmentInfo,
                          facebookReferrerInfo);
    }

    public static Event of(final ObjectId id,
                           final ActivityKey activityKey,
                           final AppInfo appInfo,
                           final ChannelInfo channelInfo,
                           final DeviceIdInfo deviceIdInfo,
                           final DeviceDetail deviceDetail,
                           final EventInfo eventInfo,
                           final EnvironmentInfo environmentInfo,
                           final FacebookReferrerInfo facebookReferrerInfo)  {
        return new Event(id,
                         activityKey,
                         appInfo,
                         channelInfo,
                         deviceIdInfo,
                         deviceDetail,
                         eventInfo,
                         environmentInfo,
                         facebookReferrerInfo);
    }
}
