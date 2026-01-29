package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoreBusinessTest {
    @Test
    void testCoursesRelatedToSpring_When_UsingAStub() {
        // Given (Arrange)
        // Criando a instância a partir da classe que implementa a interface, não da interface diretamente
        ICourseService service = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(service);
        // When (Act)
        // Filtrar os cursos
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Ana");
        // Then (Assert)
        assertEquals(4, filteredCourses.size());
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingFooBarStudent() {
        // Given (Arrange)
        // Criando a instância a partir da classe que implementa a interface, não da interface diretamente
        ICourseService service = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(service);
        // When (Act)
        // Filtrar os cursos
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Foo Bar");
        // Then (Assert)
        assertEquals(0, filteredCourses.size());
    }
}
