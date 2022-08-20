package one.appscale.relayclientapi.domain.install;

import com.google.common.collect.Lists;
import lombok.Builder;
import one.appscale.relayclientapi.domain.csv.Csv;
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static one.appscale.relayclientapi.common.support.DateTimeSupport.epochSecondToLocalDateTimeString;

public record InstallCsv(long createdAt,
                         ActivityKind activityKind,
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

    public static InstallCsv of(final Install install) {
        final ActivityKey activityKey = install.activityKey();
        final AppInfo appInfo = install.appInfo();
        final ChannelInfo channelInfo = install.channelInfo();
        final AttributionInfo attributionInfo = install.attributionInfo();
        final EngagementInfo engagementInfo = install.engagementInfo();
        final DeviceIdInfo deviceIdInfo = install.deviceIdInfo();
        final DeviceDetail deviceDetail = install.deviceDetail();
        final EnvironmentInfo environmentInfo = install.environmentInfo();
        final FacebookReferrerInfo facebookReferrerInfo = install.facebookReferrerInfo();
        return InstallCsv.builder()
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
                         .impressionAttributionWindow(attributionInfo.impressionAttributionWindow())
                         .clickAttributionWindow(attributionInfo.clickAttributionWindow())
                         .probmatchingAttributionWindow(attributionInfo.probmatchingAttributionWindow())
                         .impressionTime(engagementInfo.impressionTime())
                         .clickTime(engagementInfo.clickTime())
                         .installTime(engagementInfo.installTime())
                         .installBeginTime(engagementInfo.installBeginTime())
                         .installFinishTime(engagementInfo.installFinishTime())
                         .conversionDuration(engagementInfo.conversionDuration())
                         .referrer(engagementInfo.referrer())
                         .matchType(engagementInfo.matchType())
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
        return Arrays.stream(InstallCsv.class.getDeclaredFields()).map(Field::getName).toList();
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
