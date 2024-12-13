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

    public School retrieveById(final Integer id) {
        if(id==null){
            throw new RuntimeException(" School Id is Null");
        }
        final Optional<School> school = this.schoolRepository.findById(id);
        if(school.isPresent()){
            return school.get();
        }
        else{
            throw new IllegalArgumentException("School not found for ID"+id);
        }
    }

    public List<School> retrieveAll() {
        return this.schoolRepository.findAll();
    }

    public School updateById(final School school, final Integer id) {
        final Optional<School> schoolOptional =this.schoolRepository.findById(id);
        if(schoolOptional.isEmpty()){
            throw new RuntimeException("School not Found.");
        }
        final School schoolObject = schoolOptional.get();
        if(school.getName()!= null){
            schoolObject.setName(school.getName());
        }
        if(school.getAddress()!=null){
            schoolObject.setAddress(school.getAddress());
        }
        return schoolRepository.save(schoolObject);
    }

    public void deleteById(final Integer id) {
        if(id==null){
            throw new RuntimeException("Invalid ID");
        }
        final School school = this.schoolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("School not Found."));
        this.schoolRepository.deleteById(id);
    }
}
