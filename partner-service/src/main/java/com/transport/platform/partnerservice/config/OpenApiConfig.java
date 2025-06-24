package com.transport.platform.partnerservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Partner Platform API")
                        .version("v1.0")
                        .description("API documentation for managing partners and partner users.")
                        .contact(new Contact()
                                .name("Yogesh Kumar")
                                .email("your.email@domain.com"))
                );
    }
}
