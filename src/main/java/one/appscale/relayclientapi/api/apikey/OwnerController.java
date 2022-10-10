package one.appscale.relayclientapi.api.apikey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api._validator.apikey.HasMasterApiKey;
import one.appscale.relayclientapi.api.apikey.dto.ApiKeyResponse;
import one.appscale.relayclientapi.domain.apikey.ApiKeyDocument;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static one.appscale.relayclientapi.common.constants.CustomHttpHeaders.ADMIN_KEY_HEADER;

@Tag(name = "소유자 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/owners")
public class OwnerController {
    private final ApiKeyService service;

    @Operation(summary = "모든 소유자 조회")
    @GetMapping
    public List<String> getAllOwners(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key) {
        return service.getOwners();
    }

    @Operation(summary = "소유자 정보로 API KEY 정보 조회")
    @GetMapping("/{owner}")
    public ApiKeyResponse getByOwner(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                     @PathVariable("owner") String owner) {
        final ApiKeyDocument document = service.getOwner(owner);
        return ApiKeyResponse.of(document);
    }

    @Operation(summary = "소유자 추가 (API KEY 자동 생성)")
    @PostMapping("/{owner}")
    public ApiKeyResponse addOwner(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                   @PathVariable("owner") String owner) {
        final ApiKeyDocument document = service.addOwner(owner);
        return ApiKeyResponse.of(document);
    }

    @Operation(summary = "API KEY 로 소유자 조회")
    @GetMapping("/key/{apiKey}")
    public ApiKeyResponse getOwnerByKey(@HasMasterApiKey @RequestHeader(ADMIN_KEY_HEADER) String key,
                                        @PathVariable("apiKey") String apiKey) {
        final ApiKeyDocument document = service.getOwnerByKey(apiKey);
        return ApiKeyResponse.of(document);
    }
}
