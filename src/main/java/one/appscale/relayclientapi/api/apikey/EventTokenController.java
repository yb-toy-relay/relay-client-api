package one.appscale.relayclientapi.api.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.api.apikey.dto.EventTokenRequest;
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
@RequestMapping("/dev/event-token")
public class EventTokenController {
    private final ApiKeyService service;

    @PostMapping
    public ApiKeyResponse addEventToken(final @RequestBody EventTokenRequest request) {
        final ApiKeyDocument document = service.addEventToken(request.owner(), request.eventToken());
        return ApiKeyResponse.of(document);
    }

    @DeleteMapping
    public ApiKeyResponse removeEventToken(final @RequestBody EventTokenRequest request) {
        final ApiKeyDocument document = service.removeEventToken(request.owner(), request.eventToken());
        return ApiKeyResponse.of(document);
    }
}
