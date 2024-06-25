package com.jobintechtracking.app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Learning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<String> url;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "steps_id", referencedColumnName = "id")
    private Steps steps;

    public Learning() {

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public Steps getSteps() {
        return steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }
}
