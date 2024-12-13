package org.napbad.score.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private final int courseId;
    private final String name;

    private final List<TeachingClass> teachingClasses;

    public Course(int courseId, String name) {
        this.courseId = courseId;
        this.name = name;
        teachingClasses = new ArrayList<>();
    }
}