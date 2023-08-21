package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("запустился метод создания факультета");
        logger.debug("создали факультет: {}", faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        logger.info("запустился метод поиска факультета по id");
        logger.error("это фиаско братан" + id);
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("запустился метод редактирования факультета");
        logger.warn("метод изменит данные навсегда");
        logger.error("это фиаско братан");
        return facultyRepository.findById(faculty.getId())
                .map(i -> {
                    i.setName(faculty.getName());
                    i.setColor(faculty.getColor());
                    return facultyRepository.save(i);
                }).orElse(null);
    }

    public void removeFaculty(Long id) {
        logger.info("запустился метод удаления факультета");
        logger.warn("метод удалит данные навсегда");
        facultyRepository.deleteById(id);
        logger.debug("факультет {} удален ", id);
    }


    public Collection<Faculty> findByColorOrName(String color, String name) {
        logger.info("запустился метод поиска факультета по цвету или названию");
        return facultyRepository.findAllByColorOrNameIgnoreCase(color, name);
    }

    public Collection<Student> findStudents(Long id) {
        logger.info("запустился метод вывода списка студентов факультета");
        return facultyRepository.findAllStudentsByFaculty_Id(id);
        /* return facultyRepository.findAllStudentsByFaculty_Id(id);*/
    }

    public Collection<Faculty> getColorFaculty(String color) {
        logger.info("запустился метод вывода факультета по цвету");
        return facultyRepository.getFacultiesByColor(color);
    }

    public Optional<String> getLongNameFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length));
    }

    public Integer chapter4() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }
}
