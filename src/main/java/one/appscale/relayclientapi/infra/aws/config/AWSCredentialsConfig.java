package one.appscale.relayclientapi.infra.aws.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AWSCredentialsConfig {
    @Bean
    @Profile({"!prod"})
    public AWSCredentialsProvider localAWSCredentialsProvider() {
        return new ProfileCredentialsProvider("appscale");
    }

    @Bean
    @Profile("prod")
    public AWSCredentialsProvider defaultAwsCredentialsProvider() {
        return DefaultAWSCredentialsProviderChain.getInstance();
    }
}
