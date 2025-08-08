package com.agietsoftjpa.jpatutorial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//import java.time.Period;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder



@Table(
        name = "tbl_student",    // defining DB Table name
        uniqueConstraints = @UniqueConstraint(      // defining a unique column; Eliminates double values; The stored email defines a particular student
                name = "email_unique",
                columnNames = "email_address"
        )

)


// Student and Course will have a MANY-TO-MANY relationship
public class Student {
    @Id
    @SequenceGenerator(             // Help to Generate sequence for auto increment for every add value
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    // Add the default value
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(
            name= "email_address",       // defining DB Column name
            nullable = false            // every time we should get this particular value else it throws an exception
    )
    private String email;
    private int age;
    private LocalDate dob;
    @Embedded
    private Guardian guardian;

}
