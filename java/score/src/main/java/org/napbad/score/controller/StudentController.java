package org.napbad.score.controller;

import org.napbad.score.model.DataSource;

public class StudentController {

    private final DataSource dataSource;

    public StudentController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean login(int studentId, String studentPasswd) {
        return studentPasswd.equals("123456");
    }
}
