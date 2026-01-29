package service;

import java.util.List;

public interface ICourseService {
    // método para mostrar a lista de cursos
    public List<String> retrieveCourses(String student);
}
