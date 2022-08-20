package one.appscale.relayclientapi.domain.reattribution;

import com.google.common.collect.Lists;
import lombok.Builder;
import one.appscale.relayclientapi.domain.csv.Csv;
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public record ReattributionCsv(long createdAt,
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

    public static ReattributionCsv of(final Reattribution reattribution) {
        final ActivityKey activityKey = reattribution.activityKey();
        final AppInfo appInfo = reattribution.appInfo();
        final ChannelInfo channelInfo = reattribution.channelInfo();
        final DeviceIdInfo deviceIdInfo = reattribution.deviceIdInfo();
        final EngagementInfo engagementInfo = reattribution.engagementInfo();
        final DeviceDetail deviceDetail = reattribution.deviceDetail();
        final ReattributionInfo reattributionInfo = reattribution.reattributionInfo();
        final EnvironmentInfo environmentInfo = reattribution.environmentInfo();
        final FacebookReferrerInfo facebookReferrerInfo = reattribution.facebookReferrerInfo();
        return ReattributionCsv.builder()
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
                               .impressionTime(engagementInfo.impressionTime())
                               .clickTime(engagementInfo.clickTime())
                               .installTime(engagementInfo.installTime())
                               .installBeginTime(engagementInfo.installBeginTime())
                               .installFinishTime(engagementInfo.installFinishTime())
                               .conversionDuration(engagementInfo.conversionDuration())
                               .referrer(engagementInfo.referrer())
                               .matchType(engagementInfo.matchType())
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
                               .isReattributed(reattributionInfo.isReattributed())
                               .reattributionTime(reattributionInfo.reattributionTime())
                               .reattributionAttributionWindow(reattributionInfo.reattributionAttributionWindow())
                               .inactiveUserDefinition(reattributionInfo.inactiveUserDefinition())
                               .lastTrackerName(reattributionInfo.lastTrackerName())
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
            impressionTime,
            clickTime,
            installTime,
            installBeginTime,
            installFinishTime,
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
            reattributionTime,
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
