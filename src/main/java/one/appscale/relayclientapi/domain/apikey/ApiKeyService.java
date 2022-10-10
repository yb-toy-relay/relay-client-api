package one.appscale.relayclientapi.domain.apikey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.common.exception.ApiUnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiKeyService {
    private final ApiKeyRepository repository;

    @Value("${app.api-key.master}")
    private String master;

    public void checkValidRequest(final String apiKey, final String appToken) {
        if (apiKey.equals(master)) {
            return;
        }
        repository.findApiKeyDocumentByApiKey(apiKey)
                  .filter(doc -> doc.hasAppToken(appToken))
                  .stream()
                  .findAny()
                  .orElseThrow(ApiUnauthorizedException::new);
    }

    public ApiKeyDocument getOwnerByKey(final String apiKey) {
        return repository.findApiKeyDocumentByApiKey(apiKey)
                         .orElseThrow(OwnerNotFoundException::new);
    }

    public List<String> getOwners() {
        return repository.findAllBy()
                         .stream()
                         .map(ApiKeyDocument::getOwner)
                         .toList();
    }

    public ApiKeyDocument getOwner(final String owner) {
        return repository.findApiKeyDocumentByOwner(owner)
                         .orElseThrow(OwnerNotFoundException::new);
    }

    public ApiKeyDocument addOwner(final String owner) {
        repository.findApiKeyDocumentByOwner(owner)
                  .ifPresent(s -> {
                      throw new OwnerDuplicateException();
                  });
        return repository.save(ApiKeyDocument.of(owner));
    }

    public ApiKeyDocument addAppToken(final String owner, final String appToken) {
        final ApiKeyDocument document = repository.findApiKeyDocumentByOwner(owner)
                                                  .map(doc -> doc.addAppToken(appToken))
                                                  .orElseThrow(OwnerNotFoundException::new);
        repository.save(document);
        return document;
    }

    public ApiKeyDocument removeAppToken(final String owner, final String appToken) {
        final ApiKeyDocument document = repository.findApiKeyDocumentByOwner(owner)
                                                  .map(doc -> doc.removeAppToken(appToken))
                                                  .orElseThrow(OwnerNotFoundException::new);
        repository.save(document);
        return document;
    }

    public ApiKeyDocument addEventToken(final String owner, final String eventToken) {
        final ApiKeyDocument document = repository.findApiKeyDocumentByOwner(owner)
                                                  .map(doc -> doc.addEventToken(eventToken))
                                                  .orElseThrow(OwnerNotFoundException::new);
        repository.save(document);
        return document;
    }

    public ApiKeyDocument removeEventToken(final String owner, final String eventToken) {
        final ApiKeyDocument document = repository.findApiKeyDocumentByOwner(owner)
                                                  .map(doc -> doc.removeEventToken(eventToken))
                                                  .orElseThrow(OwnerNotFoundException::new);
        repository.save(document);
        return document;
    }
}
