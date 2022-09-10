package one.appscale.relayclientapi.api.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/keys")
public class ApiKeyController {
    private final ApiKeyService service;

    @GetMapping("/owner/{name}")
    public ApiKeyResponse getKey(final @PathVariable("name") String owner) {
        final ApiKeyDocument doc = service.getOwner(owner);
        return ApiKeyResponse.of(doc.getOwner(),
                                 doc.getApiKey(),
                                 doc.getAppTokens());
    }

    @PostMapping("/owner/{name}")
    public ApiKeyResponse addKey(final @PathVariable("name") String owner) {
        final ApiKeyDocument doc = service.addOwner(owner);
        return ApiKeyResponse.of(doc.getOwner(), doc.getApiKey());
    }

    @PutMapping("/owner/{name}/{appToken}")
    public ApiKeyResponse addAppToken(final @PathVariable("name") String owner,
                                      final @PathVariable("appToken") String appToken) {
        final ApiKeyDocument doc = service.addAppToken(owner, appToken);
        return ApiKeyResponse.of(doc.getOwner(),
                                 doc.getApiKey(),
                                 doc.getAppTokens());
    }

    @DeleteMapping("/owner/{name}/{appToken}")
    public ApiKeyResponse removeAppToken(final @PathVariable("name") String owner,
                                         final @PathVariable("appToken") String appToken) {
        final ApiKeyDocument doc = service.removeAppToken(owner, appToken);
        return ApiKeyResponse.of(doc.getOwner(),
                                 doc.getApiKey(),
                                 doc.getAppTokens());
    }
}
