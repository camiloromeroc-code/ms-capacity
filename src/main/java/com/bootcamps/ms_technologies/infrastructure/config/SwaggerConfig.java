package com.bootcamps.ms_technologies.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI msTechnologiesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS Technologies API")
                        .description("API para gestionar tecnologías")
                        .version("1.0.0"));
    }
}
