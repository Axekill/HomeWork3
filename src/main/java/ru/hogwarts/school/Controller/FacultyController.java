package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/students")
    public Collection<Student> getStudents(@PathVariable Long id) {
        return facultyService.findStudents(id);
    }

    @GetMapping("/findByColorOrName")
    public Collection<Faculty> findByColorOrName(@RequestParam(required = false) String color,
                                                 @RequestParam(required = false) String name) {
        return facultyService.findByColorOrName(color, name);
    }
    @GetMapping("/color/{color}")
    public ResponseEntity<Collection<Faculty>>getColorFaculty(@PathVariable String color) {
        Collection<Faculty> faculties = facultyService.getColorFaculty(color);
        return ResponseEntity.ok(faculties);
    }
    @GetMapping("/longname")
    public Optional<String> getLongNameFaculty() {
        return facultyService.getLongNameFaculty();
    }

    @GetMapping("/ch")
    public Integer chapter4() {
        return facultyService.chapter4();
    }
}
