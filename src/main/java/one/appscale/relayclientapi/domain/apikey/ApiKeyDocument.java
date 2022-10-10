package one.appscale.relayclientapi.domain.apikey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
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
    private Set<String> eventTokens;
    private Set<String> emailDomains;

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
            log.info("No registered appToken. owner:{}", this.owner);
            return false;
        }
        final boolean isValid = this.appTokens.contains(appToken);
        if (!isValid) {
            log.info("not registered appToken. owner:{}, appToken:{}", this.owner, appToken);
            return false;
        }
        return true;
    }

    public ApiKeyDocument addEventToken(final String eventToken) {
        if (this.eventTokens == null) {
            this.eventTokens = new HashSet<>();
        }
        this.eventTokens.add(eventToken);
        return this;
    }

    public ApiKeyDocument removeEventToken(final String eventToken) {
        this.eventTokens.remove(eventToken);
        return this;
    }

    public ApiKeyDocument addEmailDomain(final String domain) {
        if (this.emailDomains == null) {
            this.emailDomains = new HashSet<>();
        }
        this.emailDomains.add(domain);
        return this;
    }

    public ApiKeyDocument removeEmailDomain(final String domain) {
        this.emailDomains.remove(domain);
        return this;
    }

    public boolean hasEmailDomain(final String email) {
        final String emailDomain = email.split("@")[1];
        if (this.emailDomains == null) {
            log.info("No registered email domain. owner:{}", this.owner);
            return false;
        }
        final boolean isValid = this.emailDomains.contains(emailDomain);
        if (!isValid) {
            log.info("not registered email domain. owner:{}, domain:{}", this.owner, emailDomain);
            return false;
        }
        return true;
    }
}
