package com.studentsmanagement.students.service;

import com.studentsmanagement.students.entity.Student;
import com.studentsmanagement.students.repository.StudentRepository;
import jakarta.transaction.Transactional;
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
        if (!studentsOptional.isPresent()) {
            throw new RuntimeException("User not found");
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
        Optional<Student> students = this.studentsRepository.findById(id);
        if (students.isPresent()) {
            studentsRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
        this.studentsRepository.deleteById(id);
    }

}
