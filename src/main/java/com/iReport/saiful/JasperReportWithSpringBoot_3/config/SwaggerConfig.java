package com.iReport.saiful.JasperReportWithSpringBoot_3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jasper Reports API Documentation")
                        .version("1.0.0")
                        .description("API endpoints for generating dynamic reports (PDF, HTML, XLS, etc.)")
                        .contact(new Contact()
                                .name("Saiful")
                                .email("saiful@example.com")));
    }
}
