package io.github.aplaraujo.project_for_testing_people.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aplaraujo.project_for_testing_people.config.TestConfig;
import io.github.aplaraujo.project_for_testing_people.entities.Person;
import io.github.aplaraujo.project_for_testing_people.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    // Definir como a requisição será feita
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person, person1;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        person1 = new Person("Tereza", "Assis", "Alameda Venezuela, 937", "Feminino", "terezacarolinaassis@imaxbrasil.com.br");
    }


    @Test
    @Order(1)
    public void integrationTestGivenPersonObject_WhenCreateAPerson_ShouldReturnPersonObject() throws JsonProcessingException {
        var content = given()
                .spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        // Conversão do corpo da requisição de JSON para objeto
        Person createdPerson = objectMapper.readValue(content, Person.class);
        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Betina", createdPerson.getFirstName());
        assertEquals("Farias", createdPerson.getLastName());
        assertEquals("Avenida André Antônio Maggi, 636", createdPerson.getAddress());
        assertEquals("Feminino", createdPerson.getGender());
        assertEquals("betina.isabella.farias@tecnew.net", createdPerson.getEmail());
    }

    @Test
    @Order(2)
    public void integrationTestGivenPersonObject_WhenUpdateAPerson_ShouldReturnUpdatedPersonObject() throws JsonProcessingException {
        person.setFirstName("Betina Isabella");
        var content = given()
                .spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        // Conversão do corpo da requisição de JSON para objeto
        Person createdPerson = objectMapper.readValue(content, Person.class);
        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Betina Isabella", createdPerson.getFirstName());
        assertEquals("Farias", createdPerson.getLastName());
        assertEquals("Avenida André Antônio Maggi, 636", createdPerson.getAddress());
        assertEquals("Feminino", createdPerson.getGender());
        assertEquals("betina.isabella.farias@tecnew.net", createdPerson.getEmail());
    }

    @Test
    @Order(3)
    public void integrationTestGivenPersonObject_WhenFindAPersonById_ShouldReturnAPersonObject() throws JsonProcessingException {

        var content = given()
                .spec(specification)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        // Conversão do corpo da requisição de JSON para objeto
        Person createdPerson = objectMapper.readValue(content, Person.class);

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Betina Isabella", createdPerson.getFirstName());
        assertEquals("Farias", createdPerson.getLastName());
        assertEquals("Avenida André Antônio Maggi, 636", createdPerson.getAddress());
        assertEquals("Feminino", createdPerson.getGender());
        assertEquals("betina.isabella.farias@tecnew.net", createdPerson.getEmail());
    }

    @Test
    @Order(4)
    public void integrationTest_WhenFindAllPeople_ShouldReturnPeopleList() throws JsonProcessingException {

        var newContent = given()
                .spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON).body(person1)
                .when().post()
                .then().statusCode(200);

        var content = given()
                .spec(specification)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        // converter o objeto para um vetor
        List<Person> list = List.of(objectMapper.readValue(content, Person[].class));

        Person foundPersonOne = list.get(0);

        assertNotNull(foundPersonOne);
        assertNotNull(foundPersonOne.getId());
        assertNotNull(foundPersonOne.getFirstName());
        assertNotNull(foundPersonOne.getLastName());
        assertNotNull(foundPersonOne.getAddress());
        assertNotNull(foundPersonOne.getGender());
        assertNotNull(foundPersonOne.getEmail());

        assertTrue(foundPersonOne.getId() > 0);
        assertEquals("Betina Isabella", foundPersonOne.getFirstName());
        assertEquals("Farias", foundPersonOne.getLastName());
        assertEquals("Avenida André Antônio Maggi, 636", foundPersonOne.getAddress());
        assertEquals("Feminino", foundPersonOne.getGender());
        assertEquals("betina.isabella.farias@tecnew.net", foundPersonOne.getEmail());

        Person foundPersonTwo = list.get(1);

        assertNotNull(foundPersonTwo);
        assertNotNull(foundPersonTwo.getId());
        assertNotNull(foundPersonTwo.getFirstName());
        assertNotNull(foundPersonTwo.getLastName());
        assertNotNull(foundPersonTwo.getAddress());
        assertNotNull(foundPersonTwo.getGender());
        assertNotNull(foundPersonTwo.getEmail());

        assertTrue(foundPersonTwo.getId() > 0);
        assertEquals("Tereza", foundPersonTwo.getFirstName());
        assertEquals("Assis", foundPersonTwo.getLastName());
        assertEquals("Alameda Venezuela, 937", foundPersonTwo.getAddress());
        assertEquals("Feminino", foundPersonTwo.getGender());
        assertEquals("terezacarolinaassis@imaxbrasil.com.br", foundPersonTwo.getEmail());
    }
}
