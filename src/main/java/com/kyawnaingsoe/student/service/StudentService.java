package com.kyawnaingsoe.student.service;

import com.kyawnaingsoe.student.annotation.LogExecutionTime;
import com.kyawnaingsoe.student.entity.Student;
import com.kyawnaingsoe.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        super();
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(String page, String size) {
        int currentPage = 0;
        int pagerSize = 10;
        try{
            currentPage = Integer.parseInt(page);
        }
        catch(NumberFormatException nex){
            currentPage = 0;
        }
        try{
            pagerSize = Integer.parseInt(size);
        }
        catch(NumberFormatException nex){
            pagerSize = 10;
        }
        Pageable pager = PageRequest.of(currentPage, pagerSize);
        return studentRepository.findAll(pager).getContent();
    }

    @LogExecutionTime
    public void saveStudent(Student st){
        Optional<Student> s = studentRepository.findByEmail(st.getEmail());
        if(s.isPresent()) {
            throw new IllegalArgumentException("Email taken");
        }
        studentRepository.save(st);
    }

    public void deleteStudent(Long stId) {
        boolean s = studentRepository.existsById(stId);
        if(!s) {
            throw new IllegalArgumentException("Student id: " + stId + " does not exist");
        }
        studentRepository.deleteById(stId);
    }

    @Transactional
    public void updateStudent(Long stId, String name, String email) {
        Student s = studentRepository.findById(stId).orElseThrow(
                () -> new IllegalArgumentException("Student id: " + stId + " does not exist"));

        if(name != null && name.length()>0 && s.getName() != name) {
            s.setName(name);
        }

        if(email != null && email.length()>0 && s.getEmail() != email) {
            Optional<Student> st = studentRepository.findByEmail(email);
            if(st.isPresent()) {
                throw new IllegalArgumentException("Email taken");
            }

            s.setEmail(email);
        }
    }
}
