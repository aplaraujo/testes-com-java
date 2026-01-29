package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoreBusinessMockTest {
    // Mock: objeto criado para simular o comportamento de um objeto real

    ICourseService service;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setUp() {
        // Given (Arrange)
        // Simular a implementação da interface, porém sem chamá-la diretamente
        service = mock(ICourseService.class);
        business = new CourseBusiness(service);

        courses = List.of(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
        // Given (Arrange)
        when(service.retrieveCourses("Ana")).thenReturn(courses);
        // When (Act)
        // Filtrar os cursos
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Ana");
        // Then (Assert)
        assertEquals(4, filteredCourses.size());
    }

}
