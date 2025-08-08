package com.agietsoftjpa.jpatutorial.repository;

import com.agietsoftjpa.jpatutorial.entities.Course;
import com.agietsoftjpa.jpatutorial.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    /*
     *
    @Test
    public void saveTeacher() {
        Course coursePg =
                Course.builder()
                        .title("Postgres")
                        .credits(4)
                        .build();
        Course courseSpring =
                Course.builder()
                        .title("SQL")
                        .credits(5)
                        .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Moonman")
                        .lastName("Drake")
                        .courses(List.of(courseSpring, coursePg))
                        .build();
        teacherRepository.save(teacher);
    }
     */

}