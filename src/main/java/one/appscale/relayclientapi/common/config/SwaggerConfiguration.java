package one.appscale.relayclientapi.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "relay-client-api docs",
        version = "v1",
        description = "관리자용 API 만 포함"
    )
)
public class SwaggerConfiguration {
}
