package com.kyawnaingsoe.student.controller;

import com.kyawnaingsoe.student.entity.Student;
import com.kyawnaingsoe.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam String page,
                                     @RequestParam String size){
        return studentService.getStudents(page, size);
    }

    @PostMapping
    public void saveStudent(@RequestBody Student st, Errors errors) {
        studentService.saveStudent(st);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long stId) {
        studentService.deleteStudent(stId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStduent(@PathVariable("studentId") Long stId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(stId, name, email);
    }
}
