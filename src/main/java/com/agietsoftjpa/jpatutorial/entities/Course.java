package com.agietsoftjpa.jpatutorial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(             // Help to Generate sequence for auto increment for every add value
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )

    // Add the default value
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credits;

    @OneToOne(
            /*
             we don't need to create a join because it is mappedBy course field in CourseMaterial.
             Hence one-to-one mapping is already defined by the (CourseMaterial) class field below.
             "Bidirectional relationship"
             */
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    // Representing table as MANY-TO-ONE relationship
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacherCourseId",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;


    /*
    MANY-TO-MANY Relationship
     New table is created called (studentCourseMap)
     two columns with names (courseId) and (studentId) will be added to (studentCourseMap)
     By doing we remove the redundancy
     */
    @ManyToMany(
            /*
            If no Cascading ERROR: org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: com.agietsoftjpa.jpatutorial.entities.Student
             */
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "studentCourseMap",
            joinColumns = @JoinColumn(
                    name = "courseId",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "studentId",
                    referencedColumnName = "id"
            )
    )
    private List<Student> students;


    // Start the application from Main
    public void addStudents(Student student) {
        if (students == null) students = new ArrayList<>();
        students.add(student);
    }

}
