package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

// Anotação usada para inicializar as simulações anotadas com @Mock, @InjectMocks e @Captor
@ExtendWith(MockitoExtension.class)
public class CoreBusinessMockWithMockitoAnnotationTest {

    @Mock // Anotação que cria instâncias de classes ou interfaces, simulando o comportamento de uma classe ou interface
    ICourseService service;

    @InjectMocks // Cria uma instância de uma classe SUT e injeta "mocks" automaticamente nessa instância
    CourseBusiness business;

    @Captor // Anotação usada para captura de argumentos
    ArgumentCaptor<String> argumentCaptor;

    List<String> courses;

    @BeforeEach
    void setUp() {
        // Given (Arrange)
        // business = new CourseBusiness(service);

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
        given(service.retrieveCourses("Ana")).willReturn(courses);
        // When (Act)
        // Filtrar os cursos
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Ana");
        // Then (Assert)
        assertThat(filteredCourses.size(), is(4));
    }

    @DisplayName("Delete courses not related to Spring using Mockito Verify should call method")
    @Test
    void deleteCourseNotRelatedToSpringUsingMockitoVerifyShouldCallMethod() {
        // Given (Arrange)
        given(service.retrieveCourses("Ana")).willReturn(courses);
        // When (Act)
        business.deleteCourseNotRelatedToSpring("Ana");
        // Then (Assert)
        verify(service).deleteCourse("Docker para Amazon AWS Implante Apps Java e .NET com Travis CI" );
        verify(service, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker"); // Garante que um curso relacionado ao Spring nunca será excluído
    }

    @DisplayName("Delete courses not related to Spring using Mockito Verify should call method v2")
    @Test
    void deleteCourseNotRelatedToSpringUsingMockitoVerifyShouldCallMethodV2() {
        // Given (Arrange)
        given(service.retrieveCourses("Ana")).willReturn(courses);
        String courseDocker = "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI";
        String courseSpotify = "Spotify Engineering Culture Desmistificado";
        String courseSpring = "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker";
        // When (Act)
        business.deleteCourseNotRelatedToSpring("Ana");
        // Then (Assert)

        then(service).should().deleteCourse(courseDocker); // O método deve ser chamado
        then(service).should().deleteCourse(courseSpotify);
        then(service).should(never()).deleteCourse(courseSpring); // Garante que um curso relacionado ao Spring nunca será excluído
    }

    @DisplayName("Delete courses not related to Spring using Capturing Arguments should call method")
    @Test
    void deleteCourseNotRelatedToSpringCapturingArgumentsShouldCallMethod() {
        given(service.retrieveCourses("Ana")).willReturn(courses);
        argumentCaptor = ArgumentCaptor.forClass(String.class);
        // When (Act)
        business.deleteCourseNotRelatedToSpring("Ana");
        // Then (Assert)
        // Capturar um argumento após a execução
        then(service).should(times(7)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(), is(7));
    }

}
