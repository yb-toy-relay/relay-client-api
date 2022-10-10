package one.appscale.relayclientapi.api.apikey;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/owners")
public class ApiKeyController {
    private final ApiKeyService service;

    @GetMapping
    public List<String> getAllOwners() {
        return service.getOwners();
    }

    @GetMapping("/{owner}")
    public ApiKeyResponse getByOwner(final @PathVariable("owner") String owner) {
        final ApiKeyDocument document = service.getOwner(owner);
        return ApiKeyResponse.of(document);
    }

    @PostMapping("/{owner}")
    public ApiKeyResponse addOwner(final @PathVariable("owner") String owner) {
        final ApiKeyDocument document = service.addOwner(owner);
        return ApiKeyResponse.of(document);
    }

    @GetMapping("/key/{apiKey}")
    public ApiKeyResponse getOwnerByKey(@PathVariable("apiKey") String apiKey) {
        final ApiKeyDocument document = service.getOwnerByKey(apiKey);
        return ApiKeyResponse.of(document);
    }
}
