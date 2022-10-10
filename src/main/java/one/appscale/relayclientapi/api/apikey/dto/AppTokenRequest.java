package one.appscale.relayclientapi.api.apikey.dto;

import javax.validation.constraints.NotEmpty;

public record AppTokenRequest(@NotEmpty String owner,
                              @NotEmpty String appToken) {
}
