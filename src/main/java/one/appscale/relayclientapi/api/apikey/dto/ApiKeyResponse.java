package one.appscale.relayclientapi.api.apikey.dto;

import java.util.Set;

public record ApiKeyResponse(String owner,
                             String key,
                             Set<String> appTokens) {
    public static ApiKeyResponse of(final String owner,
                                    final String apiKey,
                                    final Set<String> appTokens) {
        return new ApiKeyResponse(owner, apiKey, appTokens);
    }

    public static ApiKeyResponse of(final String owner, final String apiKey) {
        return new ApiKeyResponse(owner, apiKey, null);
    }
}
