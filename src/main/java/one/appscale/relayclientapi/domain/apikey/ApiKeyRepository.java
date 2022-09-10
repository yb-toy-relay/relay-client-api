package one.appscale.relayclientapi.domain.apikey;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends MongoRepository<ApiKeyDocument, String> {
    List<ApiKeyDocument> findAllBy();

    Optional<ApiKeyDocument> findApiKeyDocumentByApiKey(String apiKey);

    Optional<ApiKeyDocument> findApiKeyDocumentByOwner(String owner);
}
