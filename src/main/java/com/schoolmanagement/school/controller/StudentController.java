package com.schoolmanagement.school.controller;

import com.schoolmanagement.school.entity.Student;
import com.schoolmanagement.school.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody final Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/retrieve/{id}")
    public Student retrieveById(@PathVariable("id") final Integer id) {
        return this.studentService.retrieveById(id);
    }

    @GetMapping("/retrieve-name")
    public List<Student> retrieveStudent(final String name) {
        System.err.println(name);
        return this.studentService.retrieveByName(name);
    }

    @GetMapping("/retrieve-search")
    public List<Student> retrieveBySearch(final String search) {
        return studentService.retrieveBySearch(search);
    }

//   /* @GetMapping("/getStudent")
//    public List<Student> getStudent(@RequestParam(required = false) final Integer id,
//                                    @RequestParam(required = false) final String name,
//                                    @RequestParam(required = false) final String address,
//                                    @RequestParam(required = false) final Long contactNumber){
//
//        List<Student> students = studentsService.getStudent(id, name, address, contactNumber);
//        return this.studentsService.getStudent(id,name,address,contactNumber);
//    }*/

    @GetMapping("/retrieve-all")
    public List<Student> retrieveAll() {
        return this.studentService.retrieveAll();
    }

    @PutMapping("/update/{id}")
    public Student updateById(@RequestBody final Student student, @PathVariable("id") final Integer id) {
        return this.studentService.updateById(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") final Integer id) {
        this.studentService.deleteById(id);
    }

    @GetMapping("/pagination")
    public Page<Student> getPaginatedData(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "2") final int size,
            @RequestParam(defaultValue = "id") final String sorting,
            @RequestParam(defaultValue = "true") final boolean direction) {
        return studentService.getPaginatedData(page, size, sorting, direction);
    }

}
