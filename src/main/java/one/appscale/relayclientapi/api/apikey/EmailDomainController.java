package one.appscale.relayclientapi.api.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.api.apikey.dto.EmailDomainRequest;
import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/email-domain")
public class EmailDomainController {
    private final ApiKeyService service;

    @PostMapping
    public ApiKeyResponse addEmailDomain(final @RequestBody EmailDomainRequest request) {
        final ApiKeyDocument document = service.addEmailDomain(request.owner(), request.emailDomain());
        return ApiKeyResponse.of(document);
    }

    @DeleteMapping
    public ApiKeyResponse removeEmailDomain(final @RequestBody EmailDomainRequest request) {
        final ApiKeyDocument document = service.removeEmailDomain(request.owner(), request.emailDomain());
        return ApiKeyResponse.of(document);
    }
}
