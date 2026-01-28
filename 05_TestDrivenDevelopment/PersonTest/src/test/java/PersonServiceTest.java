import entities.Person;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.IPersonService;
import service.PersonService;

public class PersonServiceTest {

    @DisplayName("When creating a person with success, should return a person object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // Given (Arrange)
        IPersonService service = new PersonService();
        // Incluir uma nova instância da classe Person
        Person person = new Person("Caroline Nair", "da Costa", "carolinenairdacosta@outllok.com", "Rua Alexandre Mendonça, 668", "Female");
        // When (Act)
        Person actual = service.createPerson(person);
        // Then (Assert)
        assertNotNull(actual, () -> "The createPerson() method should not return null!");
    }
}
