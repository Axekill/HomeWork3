package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAgeBetween(int min, int max);

    @Query(value = "SELECT count (*)from Student", nativeQuery = true)
    long getStudents();

    @Query(value = "select avg(age)from Student ", nativeQuery = true)
    double getAllByAge();

    @Query(value = "select * from Student ORDER BY id Desc limit 5", nativeQuery = true)
    List<Student> getStudentsLastId();

    List<Student> getStudentsByName(String name);

}
