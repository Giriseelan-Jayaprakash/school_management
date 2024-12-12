package com.schoolmanagement.school.controller;

import com.schoolmanagement.school.entity.Student;
import com.schoolmanagement.school.service.StudentService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/getStudent")
    public List<Student> getStudent(@RequestBody final String name) {
        System.err.println(name);
        return this.studentsService.getStudent(name);
    }

    @GetMapping("/search")
    public List<Student> searchStudents(final String search) {
        return studentsService.findStudents(search);
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

    @GetMapping("")
    public List<Student> getAllStudents() {
        return this.studentsService.getAllStudents();
    }

    @PutMapping("/{id}")
    public Student updateStudentById(@RequestBody final Student students, @PathVariable("id") final Integer id) {
        students.setId(id);
        return this.studentsService.updateStudentById(id, students);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable("id") final Integer id) {
        this.studentsService.deleteStudentById(id);
    }

    @GetMapping("/pagination")
    public Page<Student> getAllStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String sorting,
            @RequestParam(defaultValue = "true") boolean dire) {
        return studentsService.findAll(page, size, sorting, dire);
    }

}
