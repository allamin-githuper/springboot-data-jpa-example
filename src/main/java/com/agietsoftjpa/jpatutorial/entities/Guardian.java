package com.agietsoftjpa.jpatutorial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides(
        {
                @AttributeOverride(
                        name = "name",
                        column = @Column(name = "guardian_name")
                ),
                @AttributeOverride(
                        name = "email",
                        column = @Column(name = "guardian_email")
                ),
                @AttributeOverride(
                        name = "phone",
                        column = @Column(name = "guardian_tel")
                )
        }

)

public class Guardian {
    private String name;
    private String email;
    private String phone;
}
