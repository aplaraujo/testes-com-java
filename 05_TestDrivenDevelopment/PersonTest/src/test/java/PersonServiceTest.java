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
        // When (Act)
        // Then (Assert)
    }
}
