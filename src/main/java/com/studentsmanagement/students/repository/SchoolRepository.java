package com.studentsmanagement.students.repository;

import com.studentsmanagement.students.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {
}
