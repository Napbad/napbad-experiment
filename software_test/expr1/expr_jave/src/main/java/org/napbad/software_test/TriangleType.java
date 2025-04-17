package org.napbad.software_test;

// 判断三角形类型的类
public class TriangleType {
    // 判断三角形类型的方法，接收三个整数作为边长
    public String checkTriangle(int a, int b, int c) {
        // 检查是否能构成三角形
        if (a + b > c && a + c > b && b + c > a) {
            if (a == b && b == c) {
                return "等边三角形";
            } else if (a == b || a == c || b == c) {
                return "等腰三角形";
            } else {
                return "一般三角形";
            }
        } else {
            return "不是三角形";
        }
    }
}