package one.appscale.relayclientapi.domain.apikey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@ToString
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "api_key")
@NoArgsConstructor(access = PROTECTED)
public class ApiKeyDocument {
    @EqualsAndHashCode.Include
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String apiKey;
    @Indexed(unique = true)
    private String owner;
    private Set<String> appTokens;

    private ApiKeyDocument(final String owner) {
        this.apiKey = generateKey();
        this.owner = owner;
    }

    public static ApiKeyDocument of(final String owner) {
        return new ApiKeyDocument(owner);
    }

    private static String generateKey() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

    public ApiKeyDocument addAppToken(final String appToken) {
        if (this.appTokens == null) {
            this.appTokens = new HashSet<>();
        }
        this.appTokens.add(appToken);
        return this;
    }

    public ApiKeyDocument removeAppToken(final String appToken) {
        this.appTokens.remove(appToken);
        return this;
    }

    public boolean hasAppToken(final String appToken) {
        if (this.appTokens == null) {
            return false;
        }
        return this.appTokens.contains(appToken);
    }
}
