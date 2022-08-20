package one.appscale.relayclientapi.domain.reattribution;

import com.google.common.collect.Lists;
import lombok.Builder;
import one.appscale.relayclientapi.domain.csv.Csv;
import one.appscale.relayschema.domain.ActivityKey;
import one.appscale.relayschema.domain.AppInfo;
import one.appscale.relayschema.domain.ChannelInfo;
import one.appscale.relayschema.domain.DeviceDetail;
import one.appscale.relayschema.domain.DeviceIdInfo;
import one.appscale.relayschema.domain.EngagementInfo;
import one.appscale.relayschema.domain.EnvironmentInfo;
import one.appscale.relayschema.domain.FacebookReferrerInfo;
import one.appscale.relayschema.domain.ReattributionInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static one.appscale.relayclientapi.common.support.DateTimeSupport.epochSecondToLocalDateTimeString;

public record ReattributionCsv(long createdAt,
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
                               Long impressionTime,
                               Long clickTime,
                               Long installTime,
                               Long installBeginTime,
                               Long installFinishTime,
                               Long conversionDuration,
                               String referrer,
                               String matchType,
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
                               Integer isReattributed,
                               Long reattributionTime,
                               Integer reattributionAttributionWindow,
                               Integer inactiveUserDefinition,
                               String lastTrackerName,
                               String environment,
                               String sdkVersion,
                               String fbRefPublisherPlatform,
                               String fbRefAdObjective,
                               String fbRefCampaignGroup,
                               String fbRefCampaign,
                               String fbRefAdgroup) implements Csv {
    @Builder
    public ReattributionCsv {}

    public static ReattributionCsv of(final ReattributionDocument reattributionDocument) {
        final ActivityKey activityKey = reattributionDocument.getActivityKey();
        final AppInfo appInfo = reattributionDocument.getAppInfo();
        final ChannelInfo channelInfo = reattributionDocument.getChannelInfo();
        final DeviceIdInfo deviceIdInfo = reattributionDocument.getDeviceIdInfo();
        final EngagementInfo engagementInfo = reattributionDocument.getEngagementInfo();
        final DeviceDetail deviceDetail = reattributionDocument.getDeviceDetail();
        final ReattributionInfo reattributionInfo = reattributionDocument.getReattributionInfo();
        final EnvironmentInfo environmentInfo = reattributionDocument.getEnvironmentInfo();
        final FacebookReferrerInfo facebookReferrerInfo = reattributionDocument.getFacebookReferrerInfo();
        return ReattributionCsv.builder()
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
                               .impressionTime(engagementInfo.getImpressionTime())
                               .clickTime(engagementInfo.getClickTime())
                               .installTime(engagementInfo.getInstallTime())
                               .installBeginTime(engagementInfo.getInstallBeginTime())
                               .installFinishTime(engagementInfo.getInstallFinishTime())
                               .conversionDuration(engagementInfo.getConversionDuration())
                               .referrer(engagementInfo.getReferrer())
                               .matchType(engagementInfo.getMatchType())
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
                               .isReattributed(reattributionInfo.getIsReattributed())
                               .reattributionTime(reattributionInfo.getReattributionTime())
                               .reattributionAttributionWindow(reattributionInfo.getReattributionAttributionWindow())
                               .inactiveUserDefinition(reattributionInfo.getInactiveUserDefinition())
                               .lastTrackerName(reattributionInfo.getLastTrackerName())
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
        return Arrays.stream(ReattributionCsv.class.getDeclaredFields()).map(Field::getName).toList();
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
            epochSecondToLocalDateTimeString(impressionTime, zoneId),
            epochSecondToLocalDateTimeString(clickTime, zoneId),
            epochSecondToLocalDateTimeString(installTime, zoneId),
            epochSecondToLocalDateTimeString(installBeginTime, zoneId),
            epochSecondToLocalDateTimeString(installFinishTime, zoneId),
            conversionDuration,
            referrer,
            matchType,
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
            isReattributed,
            epochSecondToLocalDateTimeString(reattributionTime, zoneId),
            reattributionAttributionWindow,
            inactiveUserDefinition,
            lastTrackerName,
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
