package one.appscale.relayclientapi.api.apikey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api._validator.apikey.HasMasterApiKey;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.api.apikey.dto.EventTokenRequest;
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

@Tag(name = "이벤트 토큰 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/event-token")
public class EventTokenController {
    private final ApiKeyService service;

    @Operation(summary = "event token 추가")
    @PostMapping
    public ApiKeyResponse addEventToken(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                        @RequestBody EventTokenRequest request) {
        final ApiKeyDocument document = service.addEventToken(request.owner(), request.eventToken());
        return ApiKeyResponse.of(document);
    }

    @Operation(summary = "event token 제거")
    @DeleteMapping
    public ApiKeyResponse removeEventToken(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                           @RequestBody EventTokenRequest request) {
        final ApiKeyDocument document = service.removeEventToken(request.owner(), request.eventToken());
        return ApiKeyResponse.of(document);
    }
}
