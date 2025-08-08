package com.agietsoftjpa.jpatutorial.repository;

import com.agietsoftjpa.jpatutorial.entities.Guardian;
import com.agietsoftjpa.jpatutorial.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest         //  @DataJpaTest better because after testing it flushes the data and the databasse wouldn't be impacted
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    // SAVE QUERY EXAMPLES
    @Test
    public void saveStudent() {
        // Since we used the @Builder pattern in our Student Class we use the Builder pattern to save the Student Object.
        Guardian guardian = Guardian.builder()
                .name("Alfa")
                .email("alfa@gmail.com")
                .phone("238839210343")
                .build();
        Student student = Student.builder()
                .email("allamin@gmail.com")
                .firstName("Admin")
                .lastName("Administration")
                .dob(LocalDate.now())
                .age(24)
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Song")
                .email("songli@gmail.com")
                .phone("238777710343")
                .build();
        Student student = Student.builder()
                .email("daniel@gmail.com")
                .firstName("Daniel")
                .lastName("Aminde")
                .dob(LocalDate.now())
                .age(40)
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }


    // DISPLAY QUERY EXAMPLES
    @Test
    public void printStudentByFirstName() {
        List<Student> studentFirstName =
                studentRepository.findByFirstNameIgnoreCase("daniel");
        System.out.println("First name :" + studentFirstName);

    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student list: " + studentList);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> studentFirstNameContaining =
                studentRepository.findByFirstNameContaining("Al");
        System.out.println("First name containing: " + studentFirstNameContaining);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> studentGuardianName =
                studentRepository.findByGuardianName("Song");
        System.out.println("Students guardian name: " + studentGuardianName);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student =
                studentRepository.getStudentByEmailAddress("daniel@gmail.com");
        System.out.println("Student: " + student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String studentFirstname =
                studentRepository.getStudentFirstNameByEmailAddress("daniel@gmail.com");
        System.out.println("Student: " + studentFirstname);
    }
    @Test
    public void printGetStudentFirstNameByEmailAddressNative(){
        String studentFirstname =
                studentRepository.getStudentFirstNameByEmailAddressNative("daniel@gmail.com");
        System.out.println("Student first name: " + studentFirstname);
    }
    @Test
    public void printGetStudentFirstNameByEmailAddressNativeParam(){
        String studentFirstname =
                studentRepository.getStudentFirstNameByEmailAddressNativeNamedParam("daniel@gmail.com");
        System.out.println("Student first name: " + studentFirstname);
    }


    // UPDATE QUERY EXAMPLES
    @Test
    public void updateStudentNameByEmailTest(){
                studentRepository.updateStudentNameByEmail(
                        "AdminAdmin",
                        "admin@gmail.com"
                );
    }

}