package com.jobintechtracking.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String fullName;
    @ManyToOne
    private Parcours parcours;
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentStep> studentSteps;
}
