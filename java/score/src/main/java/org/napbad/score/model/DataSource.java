package org.napbad.score.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataSource {
    private int studentCounts;
    private int courseCounts;
    private int gradeCounts;
    private int teachingClassCounts;
    private int teacherCounts;
    private List<Student> students;
    private List<Course> courses;
    private List<TeachingClass> teachingClasses;
    private List<Teacher> teachers;

    private List<Admin> admins ;

    public DataSource() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        teachingClasses = new ArrayList<>();
        teachers = new ArrayList<>();
        admins = List.of(
                new Admin(1, "admin1"),
                new Admin(2, "admin2")
        );
    }
}
