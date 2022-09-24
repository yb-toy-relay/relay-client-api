package one.appscale.relayclientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class RelayClientApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RelayClientApiApplication.class, args);
    }
}
