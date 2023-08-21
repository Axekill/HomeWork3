package ru.hogwarts.school.Controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FacultyControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    FacultyRepository facultyRepository;

    @AfterEach
    public void resetDb() {
        facultyRepository.deleteAll();
    }

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void add() {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Lypoos");
        faculty.setColor("Violet");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    void getFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    void editFaculty() throws Exception {
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(faculty);
        faculty.setId(1L);
        faculty.setName("Lypoos");
        faculty.setColor("Violet");
        ResponseEntity<Faculty> facultyEntity = restTemplate.exchange("http://localhost:" + port + "/faculty",
                HttpMethod.PUT, httpFaculty, Faculty.class);
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void removeFaculty() throws Exception {
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(faculty);
        faculty.setId(1L);
        faculty.setName("Lypoos");
        faculty.setColor("Violet");
        ResponseEntity<Faculty> facultyEntity = restTemplate.exchange("http://localhost:" + port + "/faculty/1",
                HttpMethod.DELETE, httpFaculty, Faculty.class);
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getStudents() throws Exception {
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(faculty);
        faculty.setId(1L);
        faculty.setName("Lypoos");
        faculty.setColor("Violet");
        Student s1 = new Student(1L, "jkovjn", 21);
        Student s2 = new Student(2L, "bplk", 13);
        Student s3 = new Student(3L, "qmc", 20);
        s1.setFaculty(faculty);
        s2.setFaculty(faculty);
        s3.setFaculty(faculty);
        List<Student> students = new ArrayList<>(Arrays.asList(s1, s2, s3));

        faculty.setStudents(students);

        ResponseEntity<List<Faculty>> facultyEntity = restTemplate.exchange("http://localhost:" + port + "/faculty/1" + "/students",
                HttpMethod.GET,
                httpFaculty,
                new ParameterizedTypeReference<>() {
                });
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // students = Collections.singletonList(restTemplate.postForObject("http://localhost:" + port + "/student", students, Student.class));

        //  Faculty f =restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        //  Student studentActual=restTemplate.getForObject("http://localhost:"+port+"/faculty/"+f.getId()+"/student",Student.class);

        //  assertEquals(f.getStudents(),studentActual);

    }

    @Test
    void findByColorOrName() throws Exception {
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(faculty);
        faculty.setId(1L);
        faculty.setName("Lypoos");
        faculty.setColor("Violet");
        ResponseEntity<List<Faculty>> facultyEntity = restTemplate.exchange("http://localhost:" + port + "/faculty/findByColorOrName?color=Violet",
                HttpMethod.GET,
                httpFaculty,
                new ParameterizedTypeReference<>() {
                });
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}