package one.appscale.relayclientapi.infra.aws.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import one.appscale.relayclientapi.common.properties.AwsCredentialsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSCredentialsConfig {
    @Bean
    @ConditionalOnProperty(prefix = "aws.credentials", name = {"access-key-id", "secret-access-key"})
    public AWSCredentialsProvider localAWSCredentialsProvider(final AwsCredentialsProperties awsCredentialsProperties) {
        final var credentials = new BasicAWSCredentials(awsCredentialsProperties.accessKeyId(),
                                                        awsCredentialsProperties.secretAccessKey());

        return new AWSStaticCredentialsProvider(credentials);
    }

    @Bean
    @ConditionalOnMissingBean(AWSCredentialsProvider.class)
    public AWSCredentialsProvider defaultAwsCredentialsProvider() {
        return DefaultAWSCredentialsProviderChain.getInstance();
    }
}
