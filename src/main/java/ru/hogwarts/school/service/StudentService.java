package ru.hogwarts.school.service;

import org.hibernate.annotations.Synchronize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("запустился метод поиска студента по id");
        logger.error("сумашедний  id = " + id);
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("запустился метод редактирования студента");
        logger.warn("использование метода изменит студента навсегда");
        logger.debug("редактируем студента:{}", student);
        return studentRepository.findById(student.getId())
                .map(i -> {
                    i.setName(student.getName());
                    i.setAge(student.getAge());
                    i.setFaculty(student.getFaculty());
                    return studentRepository.save(i);
                }).orElse(null);

    }

    public void removeStudent(Long id) {
        logger.info("запустился метод удаления студента по id");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("запустился метод вывода списка студентов");
        return studentRepository.findAll();
    }

    public Collection<Student> findAgeBetween(int minAge, int maxAge) {
        logger.info("запустился метод поиска студентов по диапозону возраста");
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Faculty findByFaculty_Id(long id) {
        logger.info("запустился метод поиска факультета студента по id");
        return studentRepository.findById(id)
                .map(Student::getFaculty).orElse(null);
    }

    public long findByAmountStudents() {
        logger.info("запустился метод вывода количества студентов");
        return studentRepository.getStudents();
    }

    public double findByAvgAgeStudents() {
        logger.info("запустился метод вывода среднего возраста студентов");
        return studentRepository.getAllByAge();
    }

    public Collection<Student> lastFiveStudents() {
        logger.info("запустился метод вывода пяти последних студентов");
        return studentRepository.getStudentsLastId();
    }

    public Collection<Student> getName(String name) {
        logger.info("запустился метод вывода студента по имени");
        return studentRepository.getStudentsByName(name);
    }

    public Map<String, List<Student>> getNameBeginA() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().startsWith("A"))
                .collect(Collectors.groupingBy(Student::getName));
    }

    public double getAvgAgeStream() {
        return studentRepository.findAll().stream()
                .collect(
                        Collectors.averagingDouble(Student::getAge)
                );
    }

    public void getStudentOfStream() {
        var students = studentRepository.findAll()
                .stream()
                .limit(6)
                .map(Student::getName)
                .toList();

        System.out.println(students.get(0));
        System.out.println(students.get(1));


        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));

        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));

        }).start();
    }

    public void getStudentOfStreamSync() {
        var students = studentRepository.findAll()
                .stream()
                .limit(6)
                .map(Student::getName)
                .toList();

        print(students.get(0));
        print(students.get(1));


        new Thread(() -> {
            print(students.get(2));
            print(students.get(3));

        }).start();

        new Thread(() -> {
            print(students.get(4));
            print(students.get(5));

        }).start();
    }

    private synchronized void print(Object obj) {
        System.out.println(obj.toString());
    }
}
