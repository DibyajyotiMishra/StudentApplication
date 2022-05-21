package com.dibyajyoti.student.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student st1 = new Student(

                    "DibyaJyoti",
                    "dibyajyotimishra14@gmail.com",
                    LocalDate.of(2000, 06, 23));

            Student st2 = new Student(

                    "Babul",
                    "online.thydreams@gmail.com",
                    LocalDate.of(2007, 02, 12));

            studentRepository.saveAll(List.of(st1, st2));
        };
    }
}
