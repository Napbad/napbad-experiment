package org.napbad.score.model;

import lombok.Data;

import java.util.Date;

@Data
public class Grade {
    private Student student;
    private TeachingClass teachingClass;
    private int usualScore;
    private int midtermScore;
    private int experimentScore;
    private int finalScore;
    private int totalScore;
    private Date scoreDate;

    public Grade(Student student, TeachingClass teachingClass, int usualScore, int midtermScore, int experimentScore, int finalScore) {
        this.student = student;
        this.teachingClass = teachingClass;
        this.usualScore = usualScore;
        this.midtermScore = midtermScore;
        this.experimentScore = experimentScore;
        this.finalScore = finalScore;
        calculateTotalScore();
        this.scoreDate = new Date();

        calculateTotalScore();
    }

    private void calculateTotalScore() {
        totalScore = usualScore + midtermScore + experimentScore + finalScore;
    }
}