package one.appscale.relayclientapi.domain.event;

import com.google.common.collect.Lists;
import lombok.Builder;
import one.appscale.relayclientapi.domain.csv.Csv;
import one.appscale.relayschema.domain.ActivityKey;
import one.appscale.relayschema.domain.AppInfo;
import one.appscale.relayschema.domain.ChannelInfo;
import one.appscale.relayschema.domain.CustomParameter;
import one.appscale.relayschema.domain.DeviceDetail;
import one.appscale.relayschema.domain.DeviceIdInfo;
import one.appscale.relayschema.domain.EnvironmentInfo;
import one.appscale.relayschema.domain.EventInfo;
import one.appscale.relayschema.domain.FacebookReferrerInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static one.appscale.relayclientapi.common.support.DateTimeSupport.epochSecondToLocalDateTimeString;

public record EventCsv(long createdAt,
                       String activityKind,
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
                       String fbRefAdgroup,
                       String adjCustom1,
                       String adjCustom2,
                       String adjCustom3,
                       String adjCustom4,
                       String adjCustom5) implements Csv {
    @Builder
    public EventCsv {}

    public static EventCsv of(final EventDocument eventDocument) {
        final ActivityKey activityKey = eventDocument.getActivityKey();
        final AppInfo appInfo = eventDocument.getAppInfo();
        final ChannelInfo channelInfo = eventDocument.getChannelInfo();
        final DeviceIdInfo deviceIdInfo = eventDocument.getDeviceIdInfo();
        final DeviceDetail deviceDetail = eventDocument.getDeviceDetail();
        final EventInfo eventInfo = eventDocument.getEventInfo();
        final EnvironmentInfo environmentInfo = eventDocument.getEnvironmentInfo();
        final FacebookReferrerInfo facebookReferrerInfo = eventDocument.getFacebookReferrerInfo();
        final CustomParameter customParameter = eventDocument.getCustomParameter();
        return EventCsv.builder()
                       .createdAt(activityKey.getCreatedAt())
                       .activityKind(activityKey.getActivityKind())
                       .appToken(appInfo.getAppToken())
                       .appId(appInfo.getAppId())
                       .appName(appInfo.getAppName())
                       .appVersion(appInfo.getAppVersion())
                       .networkName(channelInfo.getNetworkName())
                       .campaignName(channelInfo.getCampaignName())
                       .adgroupName(channelInfo.getAdgroupName())
                       .creativeName(channelInfo.getCreativeName())
                       .adjustId(deviceIdInfo.getAdjustId())
                       .gpsAdid(deviceIdInfo.getGpsAdid())
                       .idfa(deviceIdInfo.getIdfa())
                       .idfv(deviceIdInfo.getIdfv())
                       .country(deviceDetail.getCountry())
                       .countrySubdivision(deviceDetail.getCountrySubdivision())
                       .city(deviceDetail.getCity())
                       .postalCode(deviceDetail.getPostalCode())
                       .language(deviceDetail.getLanguage())
                       .deviceName(deviceDetail.getDeviceName())
                       .deviceType(deviceDetail.getDeviceType())
                       .osName(deviceDetail.getOsName())
                       .osVersion(deviceDetail.getOsVersion())
                       .attStatus(deviceDetail.getAttStatus())
                       .connectionType(deviceDetail.getConnectionType())
                       .isp(deviceDetail.getIsp())
                       .ipAddress(deviceDetail.getIpAddress())
                       .event(eventInfo.getEvent())
                       .eventName(eventInfo.getEventName())
                       .currency(eventInfo.getCurrency())
                       .revenue(eventInfo.getRevenue())
                       .environment(environmentInfo.getEnvironment())
                       .sdkVersion(environmentInfo.getSdkVersion())
                       .fbRefPublisherPlatform(facebookReferrerInfo.getFbRefPublisherPlatform())
                       .fbRefAdObjective(facebookReferrerInfo.getFbRefAdObjective())
                       .fbRefCampaignGroup(facebookReferrerInfo.getFbRefCampaignGroup())
                       .fbRefCampaign(facebookReferrerInfo.getFbRefCampaign())
                       .fbRefAdgroup(facebookReferrerInfo.getFbRefAdgroup())
                       .adjCustom1(customParameter.getAdjCustom1())
                       .adjCustom2(customParameter.getAdjCustom2())
                       .adjCustom3(customParameter.getAdjCustom3())
                       .adjCustom4(customParameter.getAdjCustom4())
                       .adjCustom5(customParameter.getAdjCustom5())
                       .build();
    }

    @Override
    public List<String> headers() {
        return Arrays.stream(EventCsv.class.getDeclaredFields()).map(Field::getName).toList();
    }

    @Override
    public List<Object> body(String zoneId) {
        return Lists.newArrayList(
            epochSecondToLocalDateTimeString(createdAt, zoneId),
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
            fbRefAdgroup,
            adjCustom1,
            adjCustom2,
            adjCustom3,
            adjCustom4,
            adjCustom5
        );
    }
}
