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
    private final StudentRepository studentsRepository;

    public StudentService(final StudentRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student createStudents(final Student students) {
        return this.studentsRepository.save(students);
    }

    public Student getStudentById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid ID");
        }
        Optional<Student> students = this.studentsRepository.findById(id);
        if (students.isPresent()) {
            return students.get();
        } else {
            throw new IllegalArgumentException("Student not found for ID: " + id);
        }
    }

    public List<Student> getStudent(final String name) {
//        System.err.println(studentsRepository.findAll());
        return this.studentsRepository.findByNameNative(name);
//        return this.studentsRepository.findByNameNative(name);
    }

    public List<Student> findStudents(final String search) {
        return studentsRepository.findStudents(search);
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

    public List<Student> getAllStudents() {
        return this.studentsRepository.findAll();
    }

    @Transactional
    public Student updateStudentById(final Integer id, final Student students) {
        Optional<Student> studentsOptional = this.studentsRepository.findById(id);
        if (studentsOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Student student = studentsOptional.get();
        if (students.getName() != null) {
            student.setName(students.getName());
        }
        if (students.getAddress() != null) {
            student.setAddress(students.getAddress());
        }
        if (students.getContactNumber() != 0) {
            student.setContactNumber(students.getContactNumber());
        }
        return this.studentsRepository.save(student);
    }
//            students.setId(id);
//    }

    public void deleteStudentById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid ID");
        }
        final Student student = this.studentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        this.studentsRepository.deleteById(id);
    }

//    public Page<Student> findAll(Pageable pageable){
//        return studentsRepository.findAll(pageable);
//    }


    public Page<Student> findAll(int page, int size, String sorting, boolean dire) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(dire ? Sort.Direction.ASC : Sort.Direction.DESC, sorting));
        return this.studentsRepository.findAll(pageable);
    }
}
