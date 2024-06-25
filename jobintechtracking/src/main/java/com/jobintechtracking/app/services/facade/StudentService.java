package com.jobintechtracking.app.services.facade;

import com.jobintechtracking.app.entities.Student;
import com.jobintechtracking.app.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    Student updateStudent(Student student);
}
