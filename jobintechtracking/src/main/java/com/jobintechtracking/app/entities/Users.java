package com.jobintechtracking.app.entities;

import com.jobintechtracking.app.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class  Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
