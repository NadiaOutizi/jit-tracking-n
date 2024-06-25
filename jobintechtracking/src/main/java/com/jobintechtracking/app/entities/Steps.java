package com.jobintechtracking.app.entities;

import com.jobintechtracking.app.enums.StepProcess;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Steps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int durationInMinutes;
    @ManyToOne
    @JoinColumn(name = "parcours_id")
    private Parcours parcours;

    public Parcours getParcours() {
        return parcours ;
    }
    public Long getId() {
        return id;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
    public String getTitle() {
        return title;

    }
    @Enumerated(EnumType.STRING)
    private StepProcess stepProcess;


}
