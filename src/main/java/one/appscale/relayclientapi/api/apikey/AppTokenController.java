package one.appscale.relayclientapi.api.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.api.apikey.dto.AppTokenRequest;
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
@RequestMapping("/dev/app-token")
public class AppTokenController {
    private final ApiKeyService service;

    @PostMapping
    public ApiKeyResponse addAppToken(final @RequestBody AppTokenRequest request) {
        final ApiKeyDocument document = service.addAppToken(request.owner(), request.appToken());
        return ApiKeyResponse.of(document);
    }

    @DeleteMapping
    public ApiKeyResponse removeAppToken(final @RequestBody AppTokenRequest request) {
        final ApiKeyDocument document = service.removeAppToken(request.owner(), request.appToken());
        return ApiKeyResponse.of(document);
    }
}
