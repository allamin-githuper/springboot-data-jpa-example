package com.agietsoftjpa.jpatutorial.repository;

import com.agietsoftjpa.jpatutorial.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> { // We add the student class(entity) in the student StudentRepository

    // GETTING RECORDS QUERY EXAMPLES
    List<Student> findByFirstNameIgnoreCase(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByFirstNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName,
                                       String lastName);

    //JPQL query are based on the classes created and not on the database query
    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.email = ?1")
    String getStudentFirstNameByEmailAddress(String email); // we are call just one value it has to be a String and not the Student object


    //NATIVE SQL query are based on the classes created and not on the database query
    @Query(
            value = "SELECT s.first_name FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    String getStudentFirstNameByEmailAddressNative(String email);

    // Native Named query parameter
    @Query(
            value = "SELECT s.first_name FROM tbl_student s WHERE s.email_address = :email",
            nativeQuery = true
    )
    String getStudentFirstNameByEmailAddressNativeNamedParam(
            @Param("email") String email
    );


    // UPDATE QUERY EXAMPLES
    @Modifying // changes or modification of records
    @Transactional // transaction will be committed
    @Query(
            value = "UPDATE tbl_student set first_name =?1 WHERE email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmail(String firstName, String email);


    // ONE-TO-ONE RELATIONSHIP
}
