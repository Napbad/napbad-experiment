package org.napbad.score.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.napbad.score.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Generator类的单元测试
 */
public class GeneratorTest {

    private Generator generator;
    private DataSource dataSource;

    /**
     * 设置测试上下文，初始化mock对象和Generator实例
     */
    @BeforeEach
    public void setUp() {
        dataSource = mock(DataSource.class);
        generator = new Generator(dataSource);
    }

    /**
     * 测试generateData方法，使用有效输入，验证是否正确填充DataSource
     *
     */
    @Test
    public void generateData_ValidInputs_ShouldPopulateDataSource() {
        int studentCount = 100;
        int courseCount = 5;
        short gradeCount = 4;

        when(dataSource.getStudentCounts()).thenReturn(studentCount);
        when(dataSource.getCourseCounts()).thenReturn(courseCount);
        when(dataSource.getGradeCounts()).thenReturn((int) gradeCount);

        generator.generateData(studentCount, courseCount, gradeCount);

        verify(dataSource).setStudentCounts(studentCount);
        verify(dataSource).setCourseCounts(courseCount);
        verify(dataSource).setGradeCounts(gradeCount);
        verify(dataSource).setStudents(anyList());
        verify(dataSource).setCourses(anyList());
    }

    /**
     * 测试generateCourseChoosing方法，验证课程应该有学生选修
     */
    @Test
    public void generateCourseChoosing_CoursesShouldHaveStudents() {
        int studentCount = 100;
        int courseCount = 5;

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < courseCount; i++) {
            Course course = new Course(i, "Course" + i);
            courses.add(course);
        }

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            Student student = new Student(i, "Student" + i, "男");
            students.add(student);
        }

        when(dataSource.getCourses()).thenReturn(courses);
        when(dataSource.getStudents()).thenReturn(students);
        when(dataSource.getStudentCounts()).thenReturn(studentCount);

        generator.generateData(studentCount, courseCount, (short) 4);
        generator.generateCourseChoosing();

        for (Course course : courses) {
            for (TeachingClass teachingClass : course.getTeachingClasses()) {
                assertTrue(teachingClass.getTotalStudents() > 0);
                assertEquals(teachingClass.getTotalStudents(), teachingClass.getStudents().size());
            }
        }
    }

    /**
     * 测试generateGrades方法，验证学生应该有成绩
     */
    @Test
    public void generateGrades_StudentsShouldHaveGrades() {
        int studentCount = 100;
        int courseCount = 5;

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < courseCount; i++) {
            Course course = new Course(i, "Course" + i);
            courses.add(course);
        }

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            Student student = new Student(i, "Student" + i, "男");
            students.add(student);
        }

        when(dataSource.getCourses()).thenReturn(courses);
        when(dataSource.getStudents()).thenReturn(students);
        when(dataSource.getStudentCounts()).thenReturn(studentCount);

        generator.generateData(studentCount, courseCount, (short) 4);
        generator.generateCourseChoosing();
        generator.generateGrades();

        for (Student student : students) {
            for (TeachingClass teachingClass : student.getTeachingClass()) {
                assertNotNull(student.getCustomGrade(teachingClass));
            }
        }
    }

}
