package com.schoolmanagement.school.controller;

import com.schoolmanagement.school.entity.School;
import com.schoolmanagement.school.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    public School create(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/retrieve/{id}")
    public School retrieveById(@PathVariable final Integer id) {
        return this.schoolService.retrieveById(id);
    }

    @GetMapping("/retrieve-all")
    public List<School> retrieveAll() {
        return this.schoolService.retrieveAll();
    }

    @PutMapping("/update/{id}")
    public School updateById(@PathVariable final Integer id, @RequestBody final School school) {
        return this.schoolService.updateById(school, id);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") final Integer id) {
        this.schoolService.deleteById(id);
    }
}
