package io.github.aplaraujo.project_for_testing_people.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    Long personId, invalidPersonId;

    Person person, person1;

    @BeforeEach
    void setUp() {
        personId = 1L;
        invalidPersonId = 100L;
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
        given(service.findById(invalidPersonId)).willThrow(ResourceNotFoundException.class);

        // When
        ResultActions response = mockMvc.perform(get("/person/{id}", invalidPersonId));

        // Then
        response.andExpect(status().isNotFound());
    }

    @Test
    public void testGivenUpdatePerson_WhenUpdatePerson_ShouldReturnPersonObjectUpdated() throws Exception {
        // Given
        Person updatedPerson = new Person("Betina Isabella", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        given(service.findById(personId)).willReturn(person);
        given(service.update(any(Person.class))).willAnswer(invocation -> invocation.getArgument(0));

        // When
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedPerson)));

        // Then
        response.andDo(print()).andExpect(status().isOk());
        response.andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())));
        response.andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())));
        response.andExpect(jsonPath("$.address", is(updatedPerson.getAddress())));
        response.andExpect(jsonPath("$.gender", is(updatedPerson.getGender())));
        response.andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @Test
    public void testGivenNonExistentPersonId_WhenUpdatePerson_ShouldThrowNotFoundException() throws Exception {
        // Given
        Person updatedPerson = new Person("Betina Isabella", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        given(service.findById(invalidPersonId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class))).willAnswer(invocation -> invocation.getArgument(1));

        // When
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedPerson)));

        // Then
        response.andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testGivenExistentPersonId_WhenDeletePerson_ShouldReturnNoContent() throws Exception {
        // Given
        willDoNothing().given(service).delete(personId);

        // When
        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        // Then
        response.andDo(print()).andExpect(status().isNoContent());
    }
}