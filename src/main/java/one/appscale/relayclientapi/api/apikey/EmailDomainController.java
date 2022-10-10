package one.appscale.relayclientapi.api.apikey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api._validator.apikey.HasMasterApiKey;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.api.apikey.dto.EmailDomainRequest;
import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static one.appscale.relayclientapi.common.constants.CustomHttpHeaders.ADMIN_KEY_HEADER;

@Tag(name = "이메일 도메인 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/email-domain")
public class EmailDomainController {
    private final ApiKeyService service;

    @Operation(summary = "email domain 추가")
    @PostMapping
    public ApiKeyResponse addEmailDomain(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                         @RequestBody EmailDomainRequest request) {
        final ApiKeyDocument document = service.addEmailDomain(request.owner(), request.emailDomain());
        return ApiKeyResponse.of(document);
    }

    @Operation(summary = "email domain 제거")
    @DeleteMapping
    public ApiKeyResponse removeEmailDomain(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                            @RequestBody EmailDomainRequest request) {
        final ApiKeyDocument document = service.removeEmailDomain(request.owner(), request.emailDomain());
        return ApiKeyResponse.of(document);
    }
}
