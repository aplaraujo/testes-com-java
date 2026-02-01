package io.github.aplaraujo.project_for_testing_people.services;

import io.github.aplaraujo.project_for_testing_people.entities.Person;
import io.github.aplaraujo.project_for_testing_people.exceptions.ResourceNotFoundException;
import io.github.aplaraujo.project_for_testing_people.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    Person person, person1;

    @BeforeEach
    void setUp() {
        person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        person1 = new Person("Tereza", "Assis", "Alameda Venezuela, 937", "Feminino", "terezacarolinaassis@imaxbrasil.com.br");
    }

    @Test
    public void testGivenPersonObject_WhenSavePerson_ShouldReturnPersonObject() {
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person)).willReturn(person);

        Person savedPerson = service.insert(person);

        assertNotNull(savedPerson);
        assertEquals("Betina", savedPerson.getFirstName());
    }

    @Test
    public void testGivingExistingEmail_WhenSavePerson_ShouldThrowAnException() {
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person));

        assertThrows(ResourceNotFoundException.class, () -> {
            service.insert(person);
        });

        verify(repository, never()).save(any(Person.class));
    }

    @Test
    public void testGivenPeopleList_WhenFindAllPeople_ShouldReturnPeopleList() {
        given(repository.findAll()).willReturn(List.of(person, person1));

        List<Person> list = service.findAll();

        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testGivenEmptyPeopleList_WhenFindAllPeople_ShouldReturnEmptyPeopleList() {
        given(repository.findAll()).willReturn(Collections.EMPTY_LIST);

        List<Person> list = service.findAll();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testGivenPersonId_WhenFindById_ShouldReturnPerson() {
        given(repository.findById(anyLong())).willReturn(Optional.of(person));

        Person savedPerson = service.findById(1L);

        assertNotNull(savedPerson);
        assertEquals("Betina", savedPerson.getFirstName());
    }

    @Test
    public void testGivenPersonObject_WhenUpdatePerson_ShouldReturnPersonUpdated() {
        person.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person));

        person.setFirstName("Betina Isabella");

        given(repository.save(person)).willReturn(person);
        Person updatedPerson = service.update(person);

        assertNotNull(updatedPerson);
        assertEquals("Betina Isabella", updatedPerson.getFirstName());
    }

    @Test
    public void testGivenPersonId_WhenDeletePerson_ShouldReturnDeleted() {
        person.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person));

        willDoNothing().given(repository).delete(person);

        service.delete(person.getId());

        verify(repository, times(1)).delete(person);
    }
}