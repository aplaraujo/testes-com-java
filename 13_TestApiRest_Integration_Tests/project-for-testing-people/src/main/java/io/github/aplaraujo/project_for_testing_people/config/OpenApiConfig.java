package io.github.aplaraujo.project_for_testing_people.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // Definir um "bean" - objeto montado e gerenciado pelo contêiner do Spring
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hello Swagger OpenAPI")
                        .version("v1")
                        .description("Some description about your API")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0")
                                .url("")
                        )
                );
    }
}
