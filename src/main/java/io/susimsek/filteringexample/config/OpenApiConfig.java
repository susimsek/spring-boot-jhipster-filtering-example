package io.susimsek.filteringexample.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
public class OpenApiConfig {

    final Environment environment;
 
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(metaData());
    }


    private Info metaData() {
        return new Info()
                .title(environment.getProperty("api-docs.title"))
                .description(environment.getProperty("api-docs.description"))
                .version(environment.getProperty("api-docs.version"))
                .termsOfService(environment.getProperty("api-docs.terms-of-service-url"))
                .license(new License().
                        name(environment.getProperty("api-docs.license"))
                        .url(environment.getProperty("api-docs.license-url"))
                );
    }
}