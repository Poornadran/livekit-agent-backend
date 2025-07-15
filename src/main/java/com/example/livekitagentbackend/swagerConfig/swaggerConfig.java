package com.example.livekitagentbackend.swagerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Voice AI Agent API")
                        .description("Backend APIs for LiveKit-based Voice AI Agent")
                        .version("v1.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("voice-agent")
                .packagesToScan("com.example.livekitagentbackend.controller")
                .build();
    }
}
