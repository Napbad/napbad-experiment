package org.napbad.score.model;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    private final int teacherId;
    private final String name;
    private TeachingClass teachingClass;

    public Teacher(int teacherId, String name) {
        this.teacherId = teacherId;
        this.name = name;
    }
}