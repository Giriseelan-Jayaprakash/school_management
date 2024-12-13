package com.schoolmanagement.school.service;

import com.schoolmanagement.school.entity.Student;
import com.schoolmanagement.school.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(final Student student) {
        return this.studentRepository.save(student);
    }

    public Student retrieveById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid ID");
        }
        Optional<Student> student = this.studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new IllegalArgumentException("Student not found for ID: " + id);
        }
    }

    public List<Student> retrieveByName(final String name) {
//        System.err.println(studentsRepository.findAll());
        return this.studentRepository.retrieveByName(name);
//        return this.studentsRepository.findByNameNative(name);
    }

    public List<Student> retrieveBySearch(final String search) {
        return studentRepository.retrieveBySearch(search);
    }

//    /*public List<Student>getStudent(final Integer id,final String name,final String address,final Long contactNumber){
//        return this.studentsRepository.getStudent(id,name,address,contactNumber);
//    }*/

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
//    }

    public List<Student> retrieveAll() {
        return this.studentRepository.findAll();
    }

    @Transactional
    public Student updateById(final Integer id, final Student student) {
        final Optional<Student> studentOptional = this.studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        final Student studentObject = studentOptional.get();
        if (student.getName() != null) {
            studentObject.setName(student.getName());
        }
        if (student.getAddress() != null) {
            studentObject.setAddress(student.getAddress());
        }
        if (student.getContactNumber() != 0) {
            studentObject.setContactNumber(student.getContactNumber());
        }
        if(student.getSchool()!=null){
            studentObject.setSchool(student.getSchool());
        }
        return this.studentRepository.save(studentObject);
    }
    public void deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid ID");
        }
        final Student student = this.studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        this.studentRepository.deleteById(id);
    }

//    public Page<Student> findAll(Pageable pageable){
//        return studentsRepository.findAll(pageable);
//    }


    public Page<Student> getPaginatedData(final int page,final int size,final String sorting,final boolean direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, sorting));
        return this.studentRepository.findAll(pageable);
    }
}
