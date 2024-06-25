package com.jobintechtracking.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Parcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String parcoursName;
    private String parcoursDescription;

    public Parcours() {
    }

    public Parcours(String parcoursName) {
        this.parcoursName = parcoursName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getparcoursName() {
        return parcoursName;
    }

    public void setparcoursName(String parcoursName) {
        this.parcoursName = parcoursName;
    }
}
