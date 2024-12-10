package com.studentsmanagement.students.controller;

import com.studentsmanagement.students.entity.Student;
import com.studentsmanagement.students.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentsService;

    public StudentController(final StudentService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping("")
    public Student createStudents(@RequestBody final Student students) {
        return this.studentsService.createStudents(students);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") final Integer id) {
        return this.studentsService.getStudentById(id);
    }

    @GetMapping("")
    public List<Student> getAllStudents() {
        return this.studentsService.getAllStudents();
    }

    @PutMapping("/{id}")
    public Student updateStudentById(@RequestBody final Student students, @PathVariable("id") final Integer id) {
        students.setId(id);
        return this.studentsService.updateStudentById(id,students);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable("id") final Integer id) {
        this.studentsService.deleteStudentById(id);
    }
}
