package service;

import java.util.ArrayList;
import java.util.List;

// System Under Test: é um objeto, função ou componente que está sendo testado.
public class CourseBusiness {
    // Dependência (ICourseService)
    private ICourseService service;

    public CourseBusiness(ICourseService service) {
        this.service = service;
    }

    // Criar um método que usa esse serviço para obter os cursos
    public List<String> retrieveCoursesRelatedToSpring(String student) {
        var filteredCourses = new ArrayList<String>();

        if ("Foo Bar".equals(student)) {
            return filteredCourses;
        }

        var allCourses = service.retrieveCourses(student);

        // Filtrar os cursos de Spring
        for(String course: allCourses) {
            if (course.contains("Spring")) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }
}
