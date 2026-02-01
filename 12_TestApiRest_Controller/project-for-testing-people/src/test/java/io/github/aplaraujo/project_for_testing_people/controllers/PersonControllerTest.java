package io.github.aplaraujo.project_for_testing_people.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aplaraujo.project_for_testing_people.entities.Person;
import io.github.aplaraujo.project_for_testing_people.exceptions.ResourceNotFoundException;
import io.github.aplaraujo.project_for_testing_people.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    public void testGivenPeopleList_WhenFindAllPeople_ShouldReturnPeopleList() throws Exception {
        // Given
        List<Person> people = List.of(person, person1);
        given(service.findAll()).willReturn(people);

        // When
        ResultActions response = mockMvc.perform(get("/person"));

        // Then
        response.andExpect(status().isOk());
        response.andDo(print());
        response.andExpect(jsonPath("$.size()", is(people.size())));
    }

    @Test
    public void testGivenPersonId_WhenFindPersonById_ShouldReturnSearchedPersonObject() throws Exception {
        // Given
        Long personId = 1L;
        given(service.findById(personId)).willReturn(person);

        // When
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then
        response.andDo(print()).andExpect(status().isOk());
        response.andExpect(jsonPath("$.firstName", is(person.getFirstName())));
        response.andExpect(jsonPath("$.lastName", is(person.getLastName())));
        response.andExpect(jsonPath("$.address", is(person.getAddress())));
        response.andExpect(jsonPath("$.gender", is(person.getGender())));
        response.andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @Test
    public void testGivenInvalidPersonId_WhenFindPersonById_ShouldThrowNotFoundException() throws Exception {
        // Given
        Long personId = 100L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        // When
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then
        response.andExpect(status().isNotFound());
    }
}