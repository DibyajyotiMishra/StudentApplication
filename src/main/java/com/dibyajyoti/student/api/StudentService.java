package com.dibyajyoti.student.api;

import java.util.List;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "DibyaJyoti",
                        "dibyajyotimishra14@gmail.com",
                        LocalDate.of(2000, 06, 23),
                        22));
    }
}