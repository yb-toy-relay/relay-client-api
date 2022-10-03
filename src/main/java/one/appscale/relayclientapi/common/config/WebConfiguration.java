package one.appscale.relayclientapi.common.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.common.support.DateTimeSupport;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer,
                                         WebServerFactoryCustomizer<TomcatServletWebServerFactory>,
                                         Jackson2ObjectMapperBuilderCustomizer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
//                .allowedOriginPatterns("http://*.appscale.cloud",
//                                       "https://*.appscale.cloud")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
                .allowCredentials(true);
    }

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "<>[\\]^`{|}"));
    }

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateTimeSupport.DATETIME_FORMAT);

    @Override
    public void customize(final Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.simpleDateFormat(DateTimeSupport.DATETIME_FORMAT)
                                  .serializers(new LocalDateSerializer(DEFAULT_DATE_TIME_FORMATTER))
                                  .serializers(new LocalDateTimeSerializer(DEFAULT_DATE_TIME_FORMATTER))
                                  .deserializers(new LocalDateDeserializer(DEFAULT_DATE_TIME_FORMATTER))
                                  .deserializers(new LocalDateTimeDeserializer(DEFAULT_DATE_TIME_FORMATTER))
                                  .build();
    }
}
