package io.github.aplaraujo.project_for_testing_people.services;

import io.github.aplaraujo.project_for_testing_people.entities.Person;
import io.github.aplaraujo.project_for_testing_people.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
    }

    @Test
    public void testGivenPersonObject_WhenSavePerson_ShouldReturnPersonObject() {
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person)).willReturn(person);

        Person savedPerson = service.insert(person);

        assertNotNull(savedPerson);
        assertEquals("Betina", savedPerson.getFirstName());
    }
}