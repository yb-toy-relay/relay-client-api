package one.appscale.relayclientapi.infra.aws.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import one.appscale.relayclientapi.common.properties.AwsCredentialsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AWSCredentialsConfig {
    @Bean
    @Profile({"!prod"})
    public AWSCredentialsProvider localAWSCredentialsProvider(final AwsCredentialsProperties awsCredentialsProperties) {
        final var credentials = new BasicAWSCredentials(awsCredentialsProperties.accessKeyId(),
                                                        awsCredentialsProperties.secretAccessKey());
        return new AWSStaticCredentialsProvider(credentials);
    }

    @Bean
    @Profile("prod")
    public AWSCredentialsProvider defaultAwsCredentialsProvider() {
        return DefaultAWSCredentialsProviderChain.getInstance();
    }
}
