package one.appscale.relayclientapi.api.apikey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "앱 토큰 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/app-token")
public class AppTokenController {
    private final ApiKeyService service;

    @Operation(summary = "앱 토큰 추가")
    @PostMapping
    public ApiKeyResponse addAppToken(final @RequestBody AppTokenRequest request) {
        final ApiKeyDocument document = service.addAppToken(request.owner(), request.appToken());
        return ApiKeyResponse.of(document);
    }

    @Operation(summary = "앱 토큰 제거")
    @DeleteMapping
    public ApiKeyResponse removeAppToken(final @RequestBody AppTokenRequest request) {
        final ApiKeyDocument document = service.removeAppToken(request.owner(), request.appToken());
        return ApiKeyResponse.of(document);
    }
}
