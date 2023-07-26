package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        return studentRepository.findById(student.getId())
                .map(i -> {
                    i.setName(student.getName());
                    i.setAge(student.getAge());
                    i.setFaculty(student.getFaculty());
                    return studentRepository.save(i);
                }).orElse(null);
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> findAgeBetween(int minAge, int maxAge) {
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Faculty findByFaculty_Id(long id) {
        return studentRepository.findById(id)
                .map(Student::getFaculty).orElse(null);
    }

    public long findByAmountStudents() {
        return studentRepository.getStudents();
    }

    public double findByAvgAgeStudents() {
        return studentRepository.getAllByAge();
    }

    public Collection<Student> lastFiveStudents() {
        return studentRepository.getStudentsLastId();
    }

    public Collection<Student> getName(String name) {
        return studentRepository.getStudentsByName(name);
    }
}
