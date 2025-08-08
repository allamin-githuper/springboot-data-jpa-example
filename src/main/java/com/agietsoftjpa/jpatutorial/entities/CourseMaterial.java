package com.agietsoftjpa.jpatutorial.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
// If not included ERROR: org.hibernate.LazyInitializationException: could not initialize proxy [com.agietsoftjpa.jpatutorial.entities.Course#1] - no Session
public class CourseMaterial {
    @Id
    @SequenceGenerator(             // Help to Generate sequence for auto increment for every add value
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )

    // Add the default value
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    // course material cannot exist on itself but depends on course
    @OneToOne(
            // Persist references: Used if the Course table is empty
            cascade = CascadeType.ALL,
            /*
            Will not include the Course unless it is specified Else FetchType.EAGLE will provide details of both
            "Unidirectional relationship"
             */
            fetch = FetchType.LAZY,

            /*
            Whenever we try to save a Course, the Course material is required
            Here we are forced to add the Course
            Esle ERROR: org.springframework.dao.DataIntegrityViolationException: not-null property references a null or transient value : com.agietsoftjpa.jpatutorial.entities.CourseMaterial.course
             */
            optional = false

    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
