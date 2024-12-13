package org.napbad.score.controller;

import lombok.Getter;
import lombok.Setter;
import org.napbad.score.model.*;

import java.util.*;

@Setter
@Getter
public class TeachingClassController {

    private int classId;

    private final DataSource dataSource;

    public TeachingClassController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void displayGradeDistribution() {
        Map<String, Map<String, Integer>> distribution = new HashMap<>();

        // 初始化分数段
        String[] gradeRanges = {"0-59", "60-69", "70-79", "80-89", "90-100"};
        for (String range : gradeRanges) {
            distribution.putIfAbsent("平时成绩", new HashMap<>());
            distribution.putIfAbsent("期中成绩", new HashMap<>());
            distribution.putIfAbsent("实验成绩", new HashMap<>());
            distribution.putIfAbsent("期末成绩", new HashMap<>());
            distribution.putIfAbsent("总成绩", new HashMap<>());

            distribution.get("平时成绩").put(range, 0);
            distribution.get("期中成绩").put(range, 0);
            distribution.get("实验成绩").put(range, 0);
            distribution.get("期末成绩").put(range, 0);
            distribution.get("总成绩").put(range, 0);
        }

        for (Student student : dataSource.getTeachingClasses().get(classId).getStudents()) {
            updateDistribution(distribution, "平时成绩", student.getCustomGrade(
                    dataSource.getTeachingClasses().get(classId)).getUsualScore());

            updateDistribution(distribution, "期中成绩", student.getCustomGrade(
                    dataSource.getTeachingClasses().get(classId)).getMidtermScore());

            updateDistribution(distribution, "实验成绩", student.getCustomGrade(
                    dataSource.getTeachingClasses().get(classId)).getExperimentScore());

            updateDistribution(distribution, "期末成绩", student.getCustomGrade(
                    dataSource.getTeachingClasses().get(classId)).getFinalScore());

            updateDistribution(distribution, "总成绩", student.getTotalGrade());
        }

        System.out.println("成绩分布:");
        for (String gradeType : distribution.keySet()) {
            System.out.println(gradeType + ":");
            for (String range : gradeRanges) {
                System.out.println("\t" + range + ": " + distribution.get(gradeType).get(range));
            }
        }
    }

    private void updateDistribution(Map<String, Map<String, Integer>> distribution, String gradeType, double grade) {
        if (grade >= 0 && grade < 60) {
            distribution.get(gradeType).put("0-59", distribution.get(gradeType).get("0-59") + 1);
        } else if (grade >= 60 && grade < 70) {
            distribution.get(gradeType).put("60-69", distribution.get(gradeType).get("60-69") + 1);
        } else if (grade >= 70 && grade < 80) {
            distribution.get(gradeType).put("70-79", distribution.get(gradeType).get("70-79") + 1);
        } else if (grade >= 80 && grade < 90) {
            distribution.get(gradeType).put("80-89", distribution.get(gradeType).get("80-89") + 1);
        } else if (grade >= 90 && grade <= 100) {
            distribution.get(gradeType).put("90-100", distribution.get(gradeType).get("90-100") + 1);
        }
    }

    public void displayGradesMenu(Scanner scanner) {
        System.out.println("选择排序方式:");
        System.out.println("1. 按学号排序");
        System.out.println("2. 按总成绩排序");
        System.out.print("请选择: ");
        int choice = scanner.nextInt();

        SortType sortType = choice == 1 ? SortType.BY_STUDENT_ID : SortType.BY_TOTAL_GRADE;
        displayGrades(sortType);
    }


    private void displayGrades(SortType sortType) {
        List<Student> sortedStudents = new ArrayList<>(
                dataSource.getStudents().stream().filter(
                        student ->
                                student.getTeachingClass()
                                        .contains(dataSource.getTeachingClasses().get(classId))).toList());
        switch (sortType) {
            case BY_STUDENT_ID:
                sortedStudents.sort(Comparator.comparing(Student::getStudentId));
                break;
            case BY_TOTAL_GRADE:
                sortedStudents.sort(Comparator.comparingDouble(
                        student -> {
                            Student student_ = (Student) student;
                            Grade customGrade = student_.getCustomGrade(
                                    dataSource.getTeachingClasses().get(classId));
                            return customGrade != null ? customGrade.getTotalScore() : 0.0;
                        }).reversed());
                break;
            default:
                throw new IllegalArgumentException("Unsupported sort type: " + sortType);
        }

        System.out.println("学号\t姓名\t平时成绩\t期中成绩\t实验成绩\t期末成绩\t总成绩");
        sortedStudents.stream().filter(
                        student
                                -> student.getCustomGrade(dataSource.getTeachingClasses().get(classId)) != null)
                .forEach(student
                        -> {
                    System.out.printf("%s\t%s\t%d  \t%d  \t%d  \t%d  \t%d \n",
                            student.getStudentId(),
                            student.getName().length() <= 2 ? student.getName() + "\t" : student.getName(),
                            student.getCustomGrade(dataSource.getTeachingClasses().get(classId)).getUsualScore(),
                            student.getCustomGrade(dataSource.getTeachingClasses().get(classId)).getMidtermScore(),
                            student.getCustomGrade(dataSource.getTeachingClasses().get(classId)).getExperimentScore(),
                            student.getCustomGrade(dataSource.getTeachingClasses().get(classId)).getFinalScore(),
                            student.getCustomGrade(dataSource.getTeachingClasses().get(classId)).getTotalScore());
                });
    }
}
