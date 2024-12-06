package com.studentsmanagement.students.controller;

import com.studentsmanagement.students.entity.Students;
import com.studentsmanagement.students.service.StudentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(final StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping("")
    public Students createStudents(@RequestBody final Students students) {
        return this.studentsService.createStudents(students);
    }

    @GetMapping("/{id}")
    public Students getStudentById(@PathVariable("id") final Integer id) {
        return this.studentsService.getStudentById(id);
    }

    @GetMapping("")
    public List<Students> getAllStudents() {
        return this.studentsService.getAllStudents();
    }

    @PutMapping("/{id}")
    public Students updateStudentById(@RequestBody final Students students,@PathVariable("id") final Integer id) {
        return this.studentsService.updateStudentById(students, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable("id") final Integer id) {
        this.studentsService.deleteStudentById(id);
    }
}
