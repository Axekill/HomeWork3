package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {
        Faculty add = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(add);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty= facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty.getId(), faculty);
        return ResponseEntity.ok(editFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty>removeFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.removeFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("{color}")
    public ResponseEntity<Faculty> getFacultyColor(@PathVariable String color) {
        Faculty faculty= facultyService.getFacultyColor(color);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
}
