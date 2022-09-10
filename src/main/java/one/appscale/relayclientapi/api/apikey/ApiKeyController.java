package one.appscale.relayclientapi.api.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/keys/owners")
public class ApiKeyController {
    private final ApiKeyService service;

    @GetMapping
    public List<String> owners() {
        return service.getOwners();
    }

    @GetMapping("/{owner}")
    public ApiKeyResponse getKey(final @PathVariable("owner") String owner) {
        final ApiKeyDocument doc = service.getOwner(owner);
        return ApiKeyResponse.of(doc.getOwner(),
                                 doc.getApiKey(),
                                 doc.getAppTokens());
    }

    @PostMapping("/{owner}")
    public ApiKeyResponse addKey(final @PathVariable("owner") String owner) {
        final ApiKeyDocument doc = service.addOwner(owner);
        return ApiKeyResponse.of(doc.getOwner(), doc.getApiKey());
    }

    @PostMapping("/{owner}/{appToken}")
    public ApiKeyResponse addAppToken(final @PathVariable("owner") String owner,
                                      final @PathVariable("appToken") String appToken) {
        final ApiKeyDocument doc = service.addAppToken(owner, appToken);
        return ApiKeyResponse.of(doc.getOwner(),
                                 doc.getApiKey(),
                                 doc.getAppTokens());
    }

    @DeleteMapping("/{owner}/{appToken}")
    public ApiKeyResponse removeAppToken(final @PathVariable("owner") String owner,
                                         final @PathVariable("appToken") String appToken) {
        final ApiKeyDocument doc = service.removeAppToken(owner, appToken);
        return ApiKeyResponse.of(doc.getOwner(),
                                 doc.getApiKey(),
                                 doc.getAppTokens());
    }
}
