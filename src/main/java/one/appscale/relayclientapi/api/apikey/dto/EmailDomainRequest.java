package one.appscale.relayclientapi.api.apikey.dto;

import javax.validation.constraints.NotEmpty;

public record EmailDomainRequest(@NotEmpty String owner,
                                 @NotEmpty String emailDomain) {
}
