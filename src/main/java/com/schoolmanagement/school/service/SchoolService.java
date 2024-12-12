package com.schoolmanagement.school.service;

import com.schoolmanagement.school.entity.School;
import com.schoolmanagement.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(final SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School createSchool(final School school) {
        return this.schoolRepository.save(school);
    }

    public School getSchoolById(final int id) {
        Optional<School> school = this.schoolRepository.findById(id);
        return school.orElse(null);
    }

    public List<School> getSchools() {
        return this.schoolRepository.findAll();
    }

    public School updateSchoolById(final School school, final int id) {
        school.setId(id);
        return this.schoolRepository.save(school);
    }

    public void deleteSchoolById(final Integer id) {
        this.schoolRepository.deleteById(id);
    }


}
