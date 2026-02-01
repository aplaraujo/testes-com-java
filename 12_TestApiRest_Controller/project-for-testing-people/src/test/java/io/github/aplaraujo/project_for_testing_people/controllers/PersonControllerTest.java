package io.github.aplaraujo.project_for_testing_people.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aplaraujo.project_for_testing_people.entities.Person;
import io.github.aplaraujo.project_for_testing_people.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // Anotação usada para subir as dependências necessárias para testar o controlador
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc; // Requisições para o controlador

    @MockitoBean
    private PersonService service;

    @Autowired
    private ObjectMapper objectMapper; // Serialização do JSON

    Person person, person1;

    @BeforeEach
    void setUp() {
        person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        person1 = new Person("Tereza", "Assis", "Alameda Venezuela, 937", "Feminino", "terezacarolinaassis@imaxbrasil.com.br");
    }

    @Test
    public void testGivenPersonObject_WhenCreatePerson_ShouldReturnSavedPerson() throws Exception {
        // Given
        given(service.insert(any(Person.class))).willAnswer(invocation -> invocation.getArgument(0));

        // When
        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(person)));

        // Then
        response.andDo(print()).andExpect(status().isOk());
        response.andExpect(jsonPath("$.firstName", is(person.getFirstName())));
        response.andExpect(jsonPath("$.lastName", is(person.getLastName())));
        response.andExpect(jsonPath("$.address", is(person.getAddress())));
        response.andExpect(jsonPath("$.gender", is(person.getGender())));
        response.andExpect(jsonPath("$.email", is(person.getEmail())));
    }
}