package org.napbad.score.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Account {
    private final int studentId;
    private final String name;
    private final String gender;
    private final List<Grade> grades;
    private final List<Course> courses;
    private final List<TeachingClass> teachingClass;

    public Student(int studentId, String name, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.grades = new ArrayList<>();
        courses = new ArrayList<>();
        teachingClass = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public int getTotalGrade() {
        return grades.stream().mapToInt(Grade::getTotalScore).sum();
    }

    public int getTotalGrade(TeachingClass teachingClass) {
        for (Grade grade : grades) {
            if (grade.getTeachingClass() == teachingClass) {
                return grade.getTotalScore();
            }
        }
        return 0;
    }


    public Grade getCustomGrade(TeachingClass teachingClass) {
        return grades.stream().filter(grade -> grade.getTeachingClass() == teachingClass).findFirst().orElse(null);
    }

    public Grade getCustomGrade(Course course) {
        return grades.stream().filter(grade -> grade.getTeachingClass().getCourse() == course).findFirst().orElse(null);
    }
}