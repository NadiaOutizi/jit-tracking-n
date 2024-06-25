package com.jobintechtracking.app.services.Impl;

import com.jobintechtracking.app.entities.Student;
import com.jobintechtracking.app.repositories.StudentRepository;
import com.jobintechtracking.app.services.facade.StudentService;
import com.jobintechtracking.app.services.facade.StudentStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {this.studentRepository= studentRepository;}

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {return studentRepository.save(student);}
    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
