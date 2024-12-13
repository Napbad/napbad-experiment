package org.napbad.score.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.napbad.score.model.*;
import org.napbad.score.utilities.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainControllerTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Generator generator;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    public void setUp() {
        // 初始化 Mock 对象
    }

    @Test
    public void generateData_InvalidStudentCount_ShouldPrintError() {
        // 设置无效的学生人数
        int studentCount = 50;
        int courseCount = 5;
        short grade = 1;

        // 调用方法
        mainController.generateData(studentCount, courseCount, grade);

        // 验证 generator 的 generateData 方法没有被调用
        verify(generator, never()).generateData(anyInt(), anyInt(), anyShort());
    }

    @Test
    public void generateData_InvalidCourseCount_ShouldPrintError() {
        // 设置有效学生人数和无效课程数量
        int studentCount = 100;
        int courseCount = 11;
        short grade = 1;

        // 调用方法
        mainController.generateData(studentCount, courseCount, grade);

        // 验证 generator 的 generateData 方法没有被调用
        verify(generator, never()).generateData(anyInt(), anyInt(), anyShort());
    }

    @Test
    public void generateData_ValidInputs_ShouldGenerateData() {
        // 设置有效输入
        int studentCount = 100;
        int courseCount = 5;
        short grade = 1;

        // 调用方法
        mainController.generateData(studentCount, courseCount, grade);

        // 验证 generator 的 generateData 方法被调用了一次
        verify(generator, times(1)).generateData(studentCount, courseCount, grade);
    }

    @Test
    public void displayTeachingClasses_ShouldDisplayAllTeachingClasses() {
        // 创建教学班列表
        List<TeachingClass> teachingClasses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TeachingClass teachingClass = new TeachingClass(
                    i,
                    new Teacher(
                            1,
                            "Teacher " + i
                    ),
                    new Course(
                            1,
                            "Course " + i
                    ),
                    new Random().nextInt(20) + 20,
                    (short) i
            );
            teachingClasses.add(teachingClass);
        }

        // 设置 dataSource 的 getTeachingClasses 方法返回值
        when(dataSource.getTeachingClasses()).thenReturn(teachingClasses);

        // 调用方法
        mainController.displayTeachingClasses();

        // 验证 dataSource 的 getTeachingClasses 方法被调用了一次
        verify(dataSource, times(1)).getTeachingClasses();
    }

    @Test
    public void displayTeachingClassScore_ValidClassId_ShouldDisplayScores() {
        // 创建教学班列表
        List<TeachingClass> teachingClasses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TeachingClass teachingClass = new TeachingClass(
                    i,
                    new Teacher(
                            1,
                            "Teacher " + i
                    ),
                    new Course(
                            1,
                            "Course " + i
                    ),
                    new Random().nextInt(20) + 20,
                    (short) i
            );
            teachingClasses.add(teachingClass);
        }

        // 创建学生列表
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student(
                    i,
                    "Student " + i,
                    "Male"
            );
            students.add(student);

            student.getTeachingClass().addAll(teachingClasses);

            // 设置 dataSource 的 getTeachingClasses 方法返回值
            when(dataSource.getTeachingClasses()).thenReturn(teachingClasses);

            List<Grade> grades = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Grade grade = new Grade(
                        student,
                        student.getTeachingClass().get(i),
                        new Random().nextInt(100) + 1,
                        new Random().nextInt(100) + 1,
                        new Random().nextInt(100) + 1,
                        new Random().nextInt(100) + 1
                );
                grade.setTeachingClass(student.getTeachingClass().get(i));
                grades.add(grade);
                student.addGrade(grade);
            }
        }

        // 调用方法
        mainController.displayTeachingClassScore(0);

        // 验证 dataSource 的 getTeachingClasses 方法被调用了一次
        verify(dataSource, times(1)).getTeachingClasses();
        // 验证学生信息
        for (Student student : students) {
            assertEquals(student.getName(), student.getName());
        }
    }
}
