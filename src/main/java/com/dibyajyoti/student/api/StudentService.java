package com.dibyajyoti.student.api;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new IllegalStateException("Student with email " + student.getEmail() + " already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.existsById(id);
        if (!studentExists) {
            throw new IllegalStateException("Student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student existingStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exist"));
        if (name != null && name.length() > 2 && !Objects.equals(name, existingStudent.getName())) {
            existingStudent.setName(name);
        }
        if (email != null && email.length() > 2 && !Objects.equals(email, existingStudent.getEmail())) {
            boolean studentExists = studentRepository.existsById(id);
            if (!studentExists) {
                throw new IllegalStateException("Student with id " + id + " does not exist");
            }
            existingStudent.setEmail(email);
        }
    }
}