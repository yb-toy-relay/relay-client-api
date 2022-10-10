package one.appscale.relayclientapi.api.apikey.dto;

import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;

import java.util.Set;

public record ApiKeyResponse(String owner,
                             String key,
                             Set<String> appTokens,
                             Set<String> eventTokens,
                             Set<String> emailDomains) {
    public static ApiKeyResponse of(final ApiKeyDocument document) {
        return new ApiKeyResponse(document.getOwner(),
                                  document.getApiKey(),
                                  document.getAppTokens(),
                                  document.getEventTokens(),
                                  document.getEmailDomains());
    }
}
