package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student add = studentService.addStudent(student);
        return ResponseEntity.ok(add);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/findAgeBetween")
    Collection<Student> findAgeBetween(@RequestParam int minAge,
                                       @RequestParam int maxAge) {
        return studentService.findAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{id}/faculty")
    public String findByFaculty_Id(@PathVariable long id) {
        return studentService.findByFaculty_Id(id).getName();
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public long amountStudents() {
        return studentService.findByAmountStudents();
    }

    @GetMapping("/avgAge")
    public double findAvgAgeStudents() {
        return studentService.findByAvgAgeStudents();
    }

    @GetMapping("/last")
    public Collection<Student> lastFiveStudents() {
        return studentService.lastFiveStudents();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Collection<Student>> getName(@PathVariable String name) {
        Collection<Student>students= studentService.getName(name);
        return ResponseEntity.ok(students);
    }

}
