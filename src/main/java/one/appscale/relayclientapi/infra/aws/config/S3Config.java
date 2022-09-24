package one.appscale.relayclientapi.infra.aws.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Bean
    public AmazonS3 s3Client(final AWSCredentialsProvider credentials) {
        return AmazonS3ClientBuilder.standard()
                                    .withRegion(Regions.AP_NORTHEAST_2)
                                    .withCredentials(credentials)
                                    .build();
    }
}
