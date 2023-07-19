package ru.hogwarts.school.Controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    AvatarRepository avatarRepository;
    @MockBean
    FacultyRepository facultyRepository;
    @SpyBean
    StudentService studentService;
    @SpyBean
    AvatarService avatarService;
    @SpyBean
    FacultyService facultyService;
    @InjectMocks
    StudentController studentController;

    @Test
    public void saveStudentTest() throws Exception {
        final Long id = 1L;
        final String name = "jfdng";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }

    @Test
    public void getAllStudentTest() throws Exception {
        List<Student> students = List.of(
                new Student(1L, "bob", 10),
                new Student(2L, "gob", 14),
                new Student(3L, "rob", 13)
        );

        when(studentRepository.findAll()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudentTest() throws Exception {
        final Long id = 2L;
        final String name = "Andrew";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }


    @Test
    public void editStudentTest() throws Exception {
        final Long id = 2L;
        final String name = "Misha";
        final int age = 10;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());



    }

    @Test
    public void removeStudentTest() throws Exception {
        final Long id = 3L;
        final String name = "Misha";
        final int age = 20;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAgeBetweenTest() throws Exception {
        Student s1 = new Student(1L, "bob", 10);
        Student s2 = new Student(2L, "gob", 14);
        Student s3 = new Student(3L, "rob", 13);

        List<Student> student = new ArrayList<>(Arrays.asList(s1, s2, s3));
        when(studentRepository.findAllByAgeBetween(10, 13)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findAgeBetween?minAge=10&maxAge=13")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByFacultyIdTest() throws Exception {
        long id = 1L;
        String name = "rob";
        int age = 15;

        long facultyId = 1L;
        String fName = "lypoos";
        String fСolor = "violet";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("faculty", fName);
        facultyObject.put("color", fСolor);

        Faculty faculty = new Faculty();
        faculty.setName(fName);
        faculty.setColor(fСolor);
        faculty.setId(facultyId);

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

}
