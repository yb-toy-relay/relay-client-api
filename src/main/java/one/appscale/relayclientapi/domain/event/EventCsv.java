package one.appscale.relayclientapi.domain.event;

import com.google.common.collect.Lists;
import lombok.Builder;
import one.appscale.relayclientapi.domain.csv.Csv;
import one.appscale.relaydomain.domain.ActivityKey;
import one.appscale.relaydomain.domain.ActivityKind;
import one.appscale.relaydomain.domain.AppInfo;
import one.appscale.relaydomain.domain.ChannelInfo;
import one.appscale.relaydomain.domain.DeviceDetail;
import one.appscale.relaydomain.domain.DeviceIdInfo;
import one.appscale.relaydomain.domain.EnvironmentInfo;
import one.appscale.relaydomain.domain.EventInfo;
import one.appscale.relaydomain.domain.FacebookReferrerInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public record EventCsv(long createdAt,
                       ActivityKind activityKind,
                       String appToken,
                       String appId,
                       String appName,
                       String appVersion,
                       String networkName,
                       String campaignName,
                       String adgroupName,
                       String creativeName,
                       String adjustId,
                       String gpsAdid,
                       String idfa,
                       String idfv,
                       String country,
                       String countrySubdivision,
                       String city,
                       String postalCode,
                       String language,
                       String deviceName,
                       String deviceType,
                       String osName,
                       String osVersion,
                       Integer attStatus,
                       String connectionType,
                       String isp,
                       String ipAddress,
                       String event,
                       String eventName,
                       String currency,
                       Float revenue,
                       String environment,
                       String sdkVersion,
                       String fbRefPublisherPlatform,
                       String fbRefAdObjective,
                       String fbRefCampaignGroup,
                       String fbRefCampaign,
                       String fbRefAdgroup) implements Csv {
    @Builder
    public EventCsv {}

    public static EventCsv of(final Event event) {
        final ActivityKey activityKey = event.activityKey();
        final AppInfo appInfo = event.appInfo();
        final ChannelInfo channelInfo = event.channelInfo();
        final DeviceIdInfo deviceIdInfo = event.deviceIdInfo();
        final DeviceDetail deviceDetail = event.deviceDetail();
        final EventInfo eventInfo = event.eventInfo();
        final EnvironmentInfo environmentInfo = event.environmentInfo();
        final FacebookReferrerInfo facebookReferrerInfo = event.facebookReferrerInfo();
        return EventCsv.builder()
                       .createdAt(activityKey.createdAt())
                       .activityKind(activityKey.activityKind())
                       .appToken(appInfo.appToken())
                       .appId(appInfo.appId())
                       .appName(appInfo.appName())
                       .appVersion(appInfo.appVersion())
                       .networkName(channelInfo.networkName())
                       .campaignName(channelInfo.campaignName())
                       .adgroupName(channelInfo.adgroupName())
                       .creativeName(channelInfo.creativeName())
                       .adjustId(deviceIdInfo.adjustId())
                       .gpsAdid(deviceIdInfo.gpsAdid())
                       .idfa(deviceIdInfo.idfa())
                       .idfv(deviceIdInfo.idfv())
                       .country(deviceDetail.country())
                       .countrySubdivision(deviceDetail.countrySubdivision())
                       .city(deviceDetail.city())
                       .postalCode(deviceDetail.postalCode())
                       .language(deviceDetail.language())
                       .deviceName(deviceDetail.deviceName())
                       .deviceType(deviceDetail.deviceType())
                       .osName(deviceDetail.osName())
                       .osVersion(deviceDetail.osVersion())
                       .attStatus(deviceDetail.attStatus())
                       .connectionType(deviceDetail.connectionType())
                       .isp(deviceDetail.isp())
                       .ipAddress(deviceDetail.ipAddress())
                       .event(eventInfo.event())
                       .eventName(eventInfo.eventName())
                       .currency(eventInfo.currency())
                       .revenue(eventInfo.revenue())
                       .environment(environmentInfo.environment())
                       .sdkVersion(environmentInfo.sdkVersion())
                       .fbRefPublisherPlatform(facebookReferrerInfo.fbRefPublisherPlatform())
                       .fbRefAdObjective(facebookReferrerInfo.fbRefAdObjective())
                       .fbRefCampaignGroup(facebookReferrerInfo.fbRefCampaignGroup())
                       .fbRefCampaign(facebookReferrerInfo.fbRefCampaign())
                       .fbRefAdgroup(facebookReferrerInfo.fbRefAdgroup())
                       .build();
    }

    @Override
    public List<String> headers() {
        return Arrays.stream(EventCsv.class.getDeclaredFields()).map(Field::getName).toList();
    }

    @Override
    public List<Object> body(String zoneId) {
        return Lists.newArrayList(
            createdAt,
            activityKind,
            appToken,
            appId,
            appName,
            appVersion,
            networkName,
            campaignName,
            adgroupName,
            creativeName,
            adjustId,
            gpsAdid,
            idfa,
            idfv,
            country,
            countrySubdivision,
            city,
            postalCode,
            language,
            deviceName,
            deviceType,
            osName,
            osVersion,
            attStatus,
            connectionType,
            isp,
            ipAddress,
            event,
            eventName,
            currency,
            revenue,
            environment,
            sdkVersion,
            fbRefPublisherPlatform,
            fbRefAdObjective,
            fbRefCampaignGroup,
            fbRefCampaign,
            fbRefAdgroup
        );
    }
}
