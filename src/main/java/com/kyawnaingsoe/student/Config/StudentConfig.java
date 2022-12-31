package com.kyawnaingsoe.student.Config;

import com.kyawnaingsoe.student.entity.Student;
import com.kyawnaingsoe.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student kyaw = new Student("Kyaw Kyaw", "kyawkyaw@gmail.com","095141422");
            Student naingSoe = new Student("Naing Soe", "naingsoe@gmail.com", "095141423");
            Student aungAung = new Student("Aung Aung", "aungaung@gmail.com", "095141424");
            Student mgMg = new Student("Mg Mg", "mgmg@gmail.com", "095141424");

            List<Student> studentList = new ArrayList<Student>();

            studentList.add(kyaw);
            studentList.add(naingSoe);
            studentList.add(aungAung);
            studentList.add(mgMg);
            studentRepository.saveAll(studentList);
        };
    }
}