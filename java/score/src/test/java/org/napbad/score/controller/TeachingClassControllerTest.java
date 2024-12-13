package org.napbad.score.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.napbad.score.model.*;
import org.napbad.score.utilities.Generator;

/**
 * 测试 TeachingClassController 类的功能。
 */
@ExtendWith(MockitoExtension.class)
public class TeachingClassControllerTest {

    /**
     * 被测试的控制器实例。
     */
    private TeachingClassController controller;

    /**
     * 在每个测试方法之前初始化 Mock 对象和控制器实例。
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        DataSource dataSource = new DataSource();
        Generator generator = new Generator(dataSource);
        controller = new TeachingClassController(dataSource);
        generator.generateData(100, 3, (short) 5);
        generator.generateCourseChoosing();
        generator.generateGrades();
    }

    /**
     * 测试 displayGradeDistribution 方法在有效数据下是否正确打印成绩分布。
     */
    @Test
    void displayGradeDistribution_ValidData_PrintsCorrectly() {
        // Arrange
        controller.displayGradeDistribution();
    }
}
