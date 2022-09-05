package one.appscale.relayclientapi.domain.install;

import com.google.common.collect.Lists;
import lombok.Builder;
import one.appscale.relayclientapi.domain.csv.Csv;
import one.appscale.relayschema.domain.ActivityKey;
import one.appscale.relayschema.domain.AppInfo;
import one.appscale.relayschema.domain.AttributionInfo;
import one.appscale.relayschema.domain.ChannelInfo;
import one.appscale.relayschema.domain.DeviceDetail;
import one.appscale.relayschema.domain.DeviceIdInfo;
import one.appscale.relayschema.domain.EngagementInfo;
import one.appscale.relayschema.domain.EnvironmentInfo;
import one.appscale.relayschema.domain.FacebookReferrerInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static one.appscale.relayclientapi.common.support.DateTimeSupport.epochSecondToLocalDateTimeString;

public record InstallCsv(long createdAt,
                         String activityKind,
                         String appToken,
                         String appId,
                         String appName,
                         String appVersion,
                         String networkName,
                         String campaignName,
                         String adgroupName,
                         String creativeName,
                         Integer impressionAttributionWindow,
                         Integer clickAttributionWindow,
                         Integer probmatchingAttributionWindow,
                         Long impressionTime,
                         Long clickTime,
                         Long installTime,
                         Long installBeginTime,
                         Long installFinishTime,
                         Long conversionDuration,
                         String referrer,
                         String matchType,
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
                         String environment,
                         String sdkVersion,
                         String fbRefPublisherPlatform,
                         String fbRefAdObjective,
                         String fbRefCampaignGroup,
                         String fbRefCampaign,
                         String fbRefAdgroup) implements Csv {
    @Builder
    public InstallCsv {}

    public static InstallCsv of(final InstallDocument installDocument) {
        final ActivityKey activityKey = installDocument.getActivityKey();
        final AppInfo appInfo = installDocument.getAppInfo();
        final ChannelInfo channelInfo = installDocument.getChannelInfo();
        final AttributionInfo attributionInfo = installDocument.getAttributionInfo();
        final EngagementInfo engagementInfo = installDocument.getEngagementInfo();
        final DeviceIdInfo deviceIdInfo = installDocument.getDeviceIdInfo();
        final DeviceDetail deviceDetail = installDocument.getDeviceDetail();
        final EnvironmentInfo environmentInfo = installDocument.getEnvironmentInfo();
        final FacebookReferrerInfo facebookReferrerInfo = installDocument.getFacebookReferrerInfo();
        return InstallCsv.builder()
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
                         .impressionAttributionWindow(attributionInfo.getImpressionAttributionWindow())
                         .clickAttributionWindow(attributionInfo.getClickAttributionWindow())
                         .probmatchingAttributionWindow(attributionInfo.getProbmatchingAttributionWindow())
                         .impressionTime(engagementInfo.getImpressionTime())
                         .clickTime(engagementInfo.getClickTime())
                         .installTime(engagementInfo.getInstallTime())
                         .installBeginTime(engagementInfo.getInstallBeginTime())
                         .installFinishTime(engagementInfo.getInstallFinishTime())
                         .conversionDuration(engagementInfo.getConversionDuration())
                         .referrer(engagementInfo.getReferrer())
                         .matchType(engagementInfo.getMatchType())
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
                         .environment(environmentInfo.getEnvironment())
                         .sdkVersion(environmentInfo.getSdkVersion())
                         .fbRefPublisherPlatform(facebookReferrerInfo.getFbRefPublisherPlatform())
                         .fbRefAdObjective(facebookReferrerInfo.getFbRefAdObjective())
                         .fbRefCampaignGroup(facebookReferrerInfo.getFbRefCampaignGroup())
                         .fbRefCampaign(facebookReferrerInfo.getFbRefCampaign())
                         .fbRefAdgroup(facebookReferrerInfo.getFbRefAdgroup())
                         .build();
    }

    @Override
    public List<String> headers() {
        return Arrays.stream(InstallCsv.class.getDeclaredFields()).map(Field::getName).toList();
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
            impressionAttributionWindow,
            clickAttributionWindow,
            probmatchingAttributionWindow,
            epochSecondToLocalDateTimeString(impressionTime, zoneId),
            epochSecondToLocalDateTimeString(clickTime, zoneId),
            epochSecondToLocalDateTimeString(installTime, zoneId),
            epochSecondToLocalDateTimeString(installBeginTime, zoneId),
            epochSecondToLocalDateTimeString(installFinishTime, zoneId),
            conversionDuration,
            referrer,
            matchType,
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
