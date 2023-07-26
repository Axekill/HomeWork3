package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.findById(faculty.getId())
                .map(i -> {
                    i.setName(faculty.getName());
                    i.setColor(faculty.getColor());
                    return facultyRepository.save(i);
                }).orElse(null);
    }

    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }


    public Collection<Faculty> findByColorOrName(String color, String name) {
        return facultyRepository.findAllByColorOrNameIgnoreCase(color, name);
    }

    public Collection<Student> findStudents(Long id) {
        return facultyRepository.findAllStudentsByFaculty_Id(id);
       /* return facultyRepository.findAllStudentsByFaculty_Id(id);*/
    }
    public Collection<Faculty>getColorFaculty(String color) {
        return facultyRepository.getFacultiesByColor(color);
    }
}
