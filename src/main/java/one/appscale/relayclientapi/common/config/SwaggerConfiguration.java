package one.appscale.relayclientapi.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    servers = {
        @Server(url = "/", description = "Default Server URL")
    },
    info = @Info(
        title = "relay-client-api docs",
        version = "v1",
        description = "관리자용 API 만 포함"
    )
)
public class SwaggerConfiguration {
}
