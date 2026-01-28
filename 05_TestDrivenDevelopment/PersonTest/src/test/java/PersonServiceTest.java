import entities.Person;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.IPersonService;
import service.PersonService;

public class PersonServiceTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Caroline Nair", "da Costa", "carolinenairdacosta@outllok.com", "Rua Alexandre Mendonça, 668", "Female");
    }

    @DisplayName("When creating a person with success, should return a person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertNotNull(person.getId(), () -> "The ID is missing!");
        assertNotNull(actual, () -> "The createPerson() method should not return null!");
    }

    @DisplayName("When creating a person with success, should contain first name in a returned person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainFirstNameInReturnedPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The first name is different!");
    }

    @DisplayName("When creating a person with success, should contain last name in a returned person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainLastNameInReturnedPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The last name is different!");
    }

    @DisplayName("When creating a person with success, should contain email in a returned person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainEmailInReturnedPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The email is different!");
    }

    @DisplayName("When creating a person with success, should contain address in a returned person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainAddressInReturnedPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The address is different!");
    }

    @DisplayName("When creating a person with success, should contain gender in a returned person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainGenderInReturnedPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertEquals(person.getGender(), actual.getGender(), () -> "The last name is different!");
    }

    // Lançamento de exceções
    @DisplayName("When creating a person with null email, should throw IllegalArgumentException")
    @Test
    void testCreatePerson_WhenEmailIsNull_ShouldThrowIllegalArgumentException() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        person.setEmail(null);
        // When (Act)
        // Then (Assert)
        assertThrows(IllegalArgumentException.class, () -> service.createPerson(person), () -> "Empty or null email should throw IllegalArgumentException");
    }
}
