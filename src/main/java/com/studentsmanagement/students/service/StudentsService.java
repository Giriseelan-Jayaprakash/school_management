package com.studentsmanagement.students.service;

import com.studentsmanagement.students.entity.Students;
import com.studentsmanagement.students.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public StudentsService(final StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Students createStudents(final Students students) {
        return this.studentsRepository.save(students);
    }

    public Students getStudentById(final Integer id) {
        if(id==null){
            throw new IllegalArgumentException("Invalid ID");
        }
        Optional<Students> students = this.studentsRepository.findById(id);
        if(students.isPresent()){
            return students.get();
        }
        else {
            throw new IllegalArgumentException("Student not found for ID: " + id);
        }
//        if(id!=null){
//        Optional<Students> students = this.studentsRepository.findById(id);
//            return students.orElseThrow(()-> new IllegalArgumentException("Students not found for ID"+ id));
//        }
//        return null;

//        Optional<Students> students = this.studentsRepository.findById(id);
//        if(students.isEmpty()){
//            throw new IllegalArgumentException("Students not found for ID");
//        }
//        return students.get();
    }

    public List<Students> getAllStudents() {
        return this.studentsRepository.findAll();
    }

    public Students updateStudentById(final Students students, final int id) {
        students.setId(id);
        return this.studentsRepository.save(students);
    }

    public void deleteStudentById(final Integer id) {
        this.studentsRepository.deleteById(id);
    }

}
