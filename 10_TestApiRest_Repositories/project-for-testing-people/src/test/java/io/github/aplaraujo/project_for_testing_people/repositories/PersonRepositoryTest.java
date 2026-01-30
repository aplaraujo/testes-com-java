package io.github.aplaraujo.project_for_testing_people.repositories;

import io.github.aplaraujo.project_for_testing_people.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void testGivenPersonObject_whenSave_thenShouldReturnSavedPerson() {
        Person person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        Person savedPerson = repository.save(person);
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @Test
    public void testGivenPersonObject_whenFindAll_thenShouldReturnPersonList() {
        Person person = new Person("Betina", "Farias", "Avenida André Antônio Maggi, 636", "Feminino", "betina.isabella.farias@tecnew.net");
        Person person1 = new Person("Tereza", "Assis", "Alameda Venezuela, 937", "Feminino", "terezacarolinaassis@imaxbrasil.com.br");
        repository.save(person);
        repository.save(person1);

        List<Person> personList = repository.findAll();

        assertNotNull(person);
        assertEquals(20, personList.size());
    }
}