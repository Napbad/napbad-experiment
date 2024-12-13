package org.napbad.score.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeachingClass {
    private final int classId;
    private final Teacher teacher;
    private final Course course;
    private int totalStudents;
    private List<Student> students;
    private final short grade;

    public TeachingClass(int classId,
                         Teacher teacher,
                         Course course,
                         int totalStudents,
                         short grade) {
        this.classId = classId;
        this.teacher = teacher;
        this.course = course;
        this.totalStudents = totalStudents;
        this.students = new ArrayList<>();
        this.grade = grade;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String toString() {
        return String.format("[%d]. %s \t %s \t %s \t %d",
                classId,
                teacher.getName(),
                course.getName(),
                grade,
                totalStudents);
    }
}