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

    public Students getStudentById(final int id) {
        Optional<Students> students = this.studentsRepository.findById(id);
        return students.orElse(null);
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
