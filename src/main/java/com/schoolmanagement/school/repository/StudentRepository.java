package com.schoolmanagement.school.repository;

import com.schoolmanagement.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query(value = "SELECT * FROM student WHERE name = :name", nativeQuery = true)
    List<Student> findByNameNative(@Param("name") final String name);

    @Query(value = "select s FROM Student s " +
            "join s.school sc " +
            "where (:search is null or " +
            "(s.name like %:search% or s.address like %:search% " +
            "OR sc.name like %:search% or sc.address like %:search%))",
            nativeQuery = false)
    List<Student> findStudents(@Param("search") final String search);





//    List<Student> findAllByName(String name);
   /* @Query("SELECT * FROM Student  Where "
//            + "(:id is null or id = id) AND "
//            + "(:name IS NULL OR s.name LIKE %:name%) AND "
//            + "(:address IS NULL OR s.address LIKE %:address%) AND "
//            + "(:contactNumber IS NULL OR s.contactNumber = :contactNumber)")
//    List<Student> getStudent(@Param("id") Integer id,
//                                 @Param("name") String name,
//                                 @Param("address") String address,
//                                 @Param("contactNumber") Long contactNumber);*/
//
//    List<Student> getStudent(Integer id, String name, String address, Long contactNumber);
}
