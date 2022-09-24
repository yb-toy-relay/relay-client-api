package one.appscale.relayclientapi.common.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConstructorBinding
@ConfigurationProperties("aws.credentials")
@ConditionalOnProperty(prefix = "aws.credentials",
                       name = {"access-key-id", "secret-access-key"})
public record AwsCredentialsProperties(@NotBlank String accessKeyId,
                                       @NotBlank String secretAccessKey) {
}
