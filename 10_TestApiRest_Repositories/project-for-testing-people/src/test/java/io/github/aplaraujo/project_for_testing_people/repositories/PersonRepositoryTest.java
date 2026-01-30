package io.github.aplaraujo.project_for_testing_people.repositories;

import io.github.aplaraujo.project_for_testing_people.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    Person person, person1;

    @BeforeEach
    void setUp() {
        person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        person1 = new Person("Tereza", "Assis", "Alameda Venezuela, 937", "Feminino", "terezacarolinaassis@imaxbrasil.com.br");
    }

    @Test
    public void testGivenPersonObject_whenSave_thenShouldReturnSavedPerson() {
        Person savedPerson = repository.save(person);
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @Test
    public void testGivenPersonObject_whenFindAll_thenShouldReturnPersonList() {
        repository.save(person);
        repository.save(person1);

        List<Person> personList = repository.findAll();

        assertNotNull(person);
        assertEquals(20, personList.size());
    }

    @Test
    public void testGivenPersonObject_whenFindById_thenShouldReturnPerson() {
        repository.save(person);

        Person savedPerson = repository.findById(person.getId()).get();

        assertNotNull(savedPerson);
        assertEquals(savedPerson.getId(), person.getId());
    }

    @Test
    public void testGivenPersonObject_whenFindByEmail_thenShouldReturnPerson() {
        repository.save(person);

        Person savedPerson = repository.findByEmail(person.getEmail()).get();

        assertNotNull(savedPerson);
        assertEquals(savedPerson.getEmail(), person.getEmail());
    }
}