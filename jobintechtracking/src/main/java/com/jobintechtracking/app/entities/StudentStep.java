package com.jobintechtracking.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data

public class StudentStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean completed;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String taskUrl;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private Steps step;


    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStep(Steps step) {
        this.step = step;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void startStep() {
        this.startTime = LocalDateTime.now();
        this.completed = false;
    }
    public void completeStep() {
        this.endTime = LocalDateTime.now();
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Steps getStep() {
        return step;
    }

    public String getTaskUrl() { return  taskUrl;}
    public  void setTaskUrl(String taskUrl) {this.taskUrl = taskUrl;}
}
