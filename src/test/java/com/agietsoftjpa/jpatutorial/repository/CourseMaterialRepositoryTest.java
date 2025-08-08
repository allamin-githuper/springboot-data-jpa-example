package com.agietsoftjpa.jpatutorial.repository;

import com.agietsoftjpa.jpatutorial.entities.Course;
import com.agietsoftjpa.jpatutorial.entities.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course =
                Course.builder()
                        .title("DB")
                        .credits(3)
                        .build();
        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.oracle.com")
                        .course(course)
                        .build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void saveCourseMaterial2() {
        Course course =
                Course.builder()
                        .title("Spring Boot framework")
                        .credits(4)
                        .build();
        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.springboot.org")
                        .course(course)
                        .build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCoursesMaterials() {
        List<CourseMaterial> courseMaterials =
                courseMaterialRepository.findAll();
        System.out.println("Course material: " + courseMaterials);

    }

}