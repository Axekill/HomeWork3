package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    Map<Long, Student> students = new HashMap<>();
  private Long studentId=0L;
  public Student addStudent(Student student) {
      students.put(studentId, student);
      studentId++;
      return student;
  }
  public Student getStudent(Long id) {
      return students.get(id);
  }

    public Student editStudent(Long id,Student student){
        students.put(studentId, student);
        return student;
    }
    public Student removeStudent(Long id) {
        return students.remove(id);
    }


    public Student getStudentAge(int age) {
        return students.get(age);
    }
}
