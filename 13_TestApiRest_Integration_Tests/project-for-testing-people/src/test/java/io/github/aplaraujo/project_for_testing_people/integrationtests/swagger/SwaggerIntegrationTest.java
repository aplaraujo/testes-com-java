package io.github.aplaraujo.project_for_testing_people.integrationtests.swagger;

import io.github.aplaraujo.project_for_testing_people.config.TestConfig;
import io.github.aplaraujo.project_for_testing_people.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // Especificar uma porta de conexão
@ActiveProfiles("dev")
public class SwaggerIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void testShouldDisplaySwaggerUIPage() {
        // Given
        var content = given().basePath("/swagger-ui/index.html")
                .port(TestConfig.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        assertTrue(content.contains("Swagger UI"));

    }
}
