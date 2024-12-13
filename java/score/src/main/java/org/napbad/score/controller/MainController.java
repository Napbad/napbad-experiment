package org.napbad.score.controller;

import org.napbad.score.model.DataSource;
import org.napbad.score.model.TeachingClass;
import org.napbad.score.utilities.Generator;

public class MainController {

    private final DataSource dataSource;
    private final Generator generator;

    public MainController(DataSource dataSource, Generator generator) {
        this.dataSource = dataSource;
        this.generator = generator;
    }

    public void generateData(int studentCount, int courseCount, short grade) {
        if (studentCount < 100) {
            System.out.println("学生数量过少，请重新输入");
            return;
        }

        if (courseCount > 10 || courseCount < 3) {
            System.out.println("课程数量不符合要求，请重新输入");
            return;
        }

        generator.generateData(studentCount, courseCount, grade);
    }

    public void generateCourseChoosing() {
        generator.generateCourseChoosing();
    }

    public void generateGrades() {
        generator.generateGrades();
    }

    public void displayGrades() {
//        for ()
    }

    public void displayTeachingClasses() {
        for (TeachingClass teachingClass : dataSource.getTeachingClasses()) {
            System.out.println(teachingClass);
        }
    }

    public void displayTeachingClassScore(int classId) {
        TeachingClass teachingClass = dataSource.getTeachingClasses().get(classId);

        teachingClass.getStudents().forEach(student -> {
            System.out.print(student.getName() + " ");
            student.getGrades().forEach(
                    // get the grade of the teaching class
                    grade -> {
                        if (grade.getTeachingClass() == teachingClass) {
                            System.out.println(grade.getTotalScore());
                        }
                    }
            );
        });
    }

    public void displayGrade(int studentId) {

        getOneGrade(studentId, dataSource);
    }

    static void getOneGrade(int studentId, DataSource dataSource) {
        dataSource.getStudents().stream().filter(
                student -> student.getStudentId() == studentId).findFirst().ifPresent(student -> {
            System.out.println("学号\t姓名\t总成绩");
            System.out.printf("%s\t%s\t%d\n", student.getStudentId(), student.getName(), student.getTotalGrade());
            System.out.println("----------------单科----------------");
            for (int i = 0; i < student.getTeachingClass().size(); i++) {
                System.out.println("科目：    " + student.getTeachingClass().get(i).getCourse().getName());
                System.out.println("\t平时成绩： " + student.getCustomGrade(student.getTeachingClass().get(i)).getUsualScore());
                System.out.println("\t实验成绩： " + student.getCustomGrade(student.getTeachingClass().get(i)).getExperimentScore());
                System.out.println("\t期中成绩： " + student.getCustomGrade(student.getTeachingClass().get(i)).getMidtermScore());
                System.out.println("\t期末成绩： " + student.getCustomGrade(student.getTeachingClass().get(i)).getFinalScore());
                System.out.println("\t单科总成绩：" + student.getCustomGrade(student.getTeachingClass().get(i)).getTotalScore());
            }
        });
    }
}
