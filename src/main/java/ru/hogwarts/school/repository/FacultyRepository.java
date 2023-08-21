package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColorOrNameIgnoreCase(String color, String name);


  @Query("select s from Student s join Faculty f on s.faculty.id=f.id where f.id=:id")
  Collection<Student> findAllStudentsByFaculty_Id(Long id);

    List<Faculty> getFacultiesByColor(String color);

}
