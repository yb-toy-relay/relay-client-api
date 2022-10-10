package one.appscale.relayclientapi.api.apikey.dto;

import javax.validation.constraints.NotEmpty;

public record EventTokenRequest(@NotEmpty String owner,
                                @NotEmpty String eventToken) {
}
