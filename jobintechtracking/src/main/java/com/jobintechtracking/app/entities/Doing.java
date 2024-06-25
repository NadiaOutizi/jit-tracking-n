package com.jobintechtracking.app.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class  Doing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "steps_id", referencedColumnName = "id")
    private Steps steps;

    public Doing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTask(){
        return task;
    }
    public void setTask(String task) {
        this.task=task;
    }
    public Steps getSteps() {
        return steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }
}
