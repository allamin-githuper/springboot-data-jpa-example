package com.agietsoftjpa.jpatutorial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// One teacher can teach multiple courses
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )


    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;

    /*
        Uni directional one-to-many mapping
        ONE-TO-MANY Relationship

        @OneToMany(
                cascade = CascadeType.ALL
        )
        @JoinColumn(
                name = "teacherCourseId",
                referencedColumnName = "teacherId"
        )
        private List<Course>courses;
     */

    /*
        Best practice it is better to go for a many-to-one relationship
        as specified by Spring Boot Doc
     */

    // MANY-TO-ONE Relationship

}
