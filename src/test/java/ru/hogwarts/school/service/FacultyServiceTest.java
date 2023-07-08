package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {
FacultyService service=new FacultyService(facultyRepository);
    @Test
    void addFaculty() {
        service.addFaculty(new Faculty(1L,"fggd","red"));
        service.addFaculty(new Faculty(2L,"dfrr","blue"));
        assertEquals(2,service.facultyMap.size());
    }

    @Test
    void getFaculty() {
       var expected= service.addFaculty(new Faculty(1L,"fggd","red"));
       assertEquals(expected,service.getFaculty(1L));
    }

    @Test
    void editFaculty() {
       var actual= service.editFaculty(new Faculty(3L,"hdcvbhj","green"));
       assertEquals(1,service.facultyMap.size());
        assertEquals(3,actual.getId());
    }

    @Test
    void removeFaculty() {
        service.addFaculty(new Faculty(1L,"fggd","red"));
        service.addFaculty(new Faculty(2L,"dfrr","blue"));
        assertEquals(2,service.facultyMap.size());
        service.removeFaculty(1L);
        assertEquals(1,service.facultyMap.size());
    }

    @Test
    void getFacultyColor() {
       var actual= service.addFaculty(new Faculty(1L,"fggd","red"));
        assertEquals("red",actual.getColor());    }
}