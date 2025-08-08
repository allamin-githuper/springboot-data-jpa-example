package com.agietsoftjpa.jpatutorial.repository;

import com.agietsoftjpa.jpatutorial.entities.Course;
import com.agietsoftjpa.jpatutorial.entities.Guardian;
import com.agietsoftjpa.jpatutorial.entities.Student;
import com.agietsoftjpa.jpatutorial.entities.Teacher;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCourses() {
        List<Course> courses =
                courseRepository.findAll();
        System.out.println("Courses: " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Lookman")
                .lastName("Orono")
                .build();
        Course course = Course.builder()
                .title("JavaScript")
                .credits(5)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void saveCourseWithTeacher2() {
        Teacher teacher = Teacher.builder()
                .firstName("Mono")
                .lastName("Drake")
                .build();
        Course course = Course.builder()
                .title("Postgres")
                .credits(5)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void saveCourseWithTeacher3() {
        Teacher teacher = Teacher.builder()
                .firstName("Bob")
                .lastName("Marley")
                .build();
        Course course = Course.builder()
                .title("SQL")
                .credits(4)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    // PAGINATION
    @Test
    public void findAllPagination() {

        // Three page pagination

        Pageable pageWithThreeRecords =
                PageRequest.of(0, 3);

        List<Course> courses3pages =
                courseRepository.findAll(pageWithThreeRecords)
                        .getContent();
        long totalElements1 =
                courseRepository.findAll(pageWithThreeRecords)
                        .getTotalElements();
        long totalPages1 =
                courseRepository.findAll(pageWithThreeRecords)
                        .getTotalPages();

        // Two page pagination

        Pageable pageWithTwoRecords =
                PageRequest.of(0, 2);

        List<Course> courses2pages =
                courseRepository.findAll(pageWithTwoRecords)
                        .getContent();
        long totalElements2 =
                courseRepository.findAll(pageWithTwoRecords)
                        .getTotalElements();
        long totalPages2 =
                courseRepository.findAll(pageWithTwoRecords)
                        .getTotalPages();

        /*
        *
        * Possible ERROR:
            java: cannot find symbol
            symbol:   variable courses3page
         */
        System.out.println(
                "Pagination 3 course:"
                        + courses3pages
                        + "\nTotal list element: "
                        + totalElements1
                        + "\nTotal pages: "
                        + totalPages1
        );

        System.out.println(
                "\nPagination 2 course:"
                        + courses2pages
                        + "\nTotal list element: "
                        + totalElements2
                        + "\nTotal pages: "
                        + totalPages2
        );

    }

    @Test
    public void findAllWithSorting() {
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        3,
                        Sort.by("title")
                );
        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit")
                );
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                                .descending()
                                .and(Sort.by("credit"))
                );

        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses:" + courses);
    }

    @Test
    public void findByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);
        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "J",
                        firstPageTenRecords).getContent();
        System.out.println("Courses:" + courses);
    }

    // QUERY MANY-TO-MANY Relationship
    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Floo")
                .lastName("Rider")
                .build();
        Guardian guardian = Guardian.builder()
                .name("Jamal Rock")
                .email("jamalek@gmail.com")
                .phone("40987644322")
                .build();
        Student student = Student.builder()
                .firstName("Salman")
                .lastName("Ripper")
                .email("salmanrip@gmail.com")
                .age(28)
                .dob(LocalDate.of(1980, 12, 5))
                .guardian(guardian)
                .build();
        Course course =
                Course.builder()
                        .title("Cyber security")
                        .credits(8)
                        .teacher(teacher)
                        .build();
        course.addStudents(student);

        courseRepository.save(course);
    }

}