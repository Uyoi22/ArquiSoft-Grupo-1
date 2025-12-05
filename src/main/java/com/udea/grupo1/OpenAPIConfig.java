package com.udea.grupo1;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API clima de una ciudad")
                        .version("1.0.0")
                        .description("Documentación de la API para obtener el clima de una ciudad específica."));
    }
}
