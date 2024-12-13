package org.napbad.score.controller;

import lombok.Setter;
import org.napbad.score.model.DataSource;
import org.napbad.score.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentScoreController {

    private final DataSource dataSource;
    @Setter
    private int studentId;

    public StudentScoreController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void displayGrade() {
        MainController.getOneGrade(studentId, dataSource);
    }

}
