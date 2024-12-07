package com.studentsmanagement.students.controller;

import com.studentsmanagement.students.entity.School;
import com.studentsmanagement.students.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(final SchoolService schoolService) {
        this.schoolService = schoolService;
    }//

    @PostMapping("")
    public School createSchool(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }
    @GetMapping("/id/{id}")
    public School getSchoolById(@PathVariable final Integer id) {
        return this.schoolService.getSchoolById(id);
    }
    @GetMapping("")
    public List<School> getSchools(){
        return this.schoolService.getSchools();
    }
    @PutMapping("/id/{id}")
    public School updateSchoolById(@PathVariable final Integer id,@RequestBody final School school){
        return this.schoolService.updateSchoolById(school,id);
    }
    @DeleteMapping("/id/{id}")
    public void deleteSchoolById(@PathVariable("id") final Integer id){
        this.schoolService.deleteSchoolById(id);
    }
}
