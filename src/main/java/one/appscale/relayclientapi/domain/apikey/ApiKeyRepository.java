package one.appscale.relayclientapi.domain.apikey;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApiKeyRepository extends MongoRepository<ApiKeyDocument, String> {
    Optional<ApiKeyDocument> findApiKeyDocumentByApiKey(String apiKey);

    Optional<ApiKeyDocument> findApiKeyDocumentByOwner(String owner);
}
