package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    Map<Long, Faculty> facultyMap = new HashMap<>();
    Long facultyId = 0L;
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++facultyId);
        facultyMap.put(facultyId, faculty);
        return faculty;
    }
    public  Faculty getFaculty(Long id) {
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        facultyMap.put(facultyId, faculty);
        return faculty;
    }

    public Faculty removeFaculty(Long facultyId) {
        return facultyMap.remove(facultyId);
    }

    public Collection<Faculty> getFacultyColor(String color) {
        return facultyMap.values().stream()
                .filter(i->i.getColor()==color)
                .collect(Collectors.toList());
    }
}
