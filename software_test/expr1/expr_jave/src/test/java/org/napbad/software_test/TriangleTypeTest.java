package org.napbad.software_test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TriangleTypeTest {
    @Test
    public void testEquilateralTriangle() {
        TriangleType triangle = new TriangleType();
        String result = triangle.checkTriangle(3, 3, 3);
        assertEquals("等边三角形", result);
    }

    @Test
    public void testIsoscelesTriangle() {
        TriangleType triangle = new TriangleType();
        String result = triangle.checkTriangle(3, 3, 4);
        assertEquals("等腰三角形", result);
    }

    @Test
    public void testScaleneTriangle() {
        TriangleType triangle = new TriangleType();
        String result = triangle.checkTriangle(3, 4, 5);
        assertEquals("一般三角形", result);
    }

    @Test
    public void testNotTriangle() {
        TriangleType triangle = new TriangleType();
        String result = triangle.checkTriangle(1, 2, 4);
        assertEquals("不是三角形", result);
    }
}