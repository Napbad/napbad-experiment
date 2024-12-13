package org.napbad.score.view;

import org.napbad.score.controller.StudentScoreController;
import org.napbad.score.model.DataSource;

import java.util.Scanner;

public class StudentScoreView {

    private final StudentScoreController controller;
    private final DataSource dataSource;
    private int studentId;

    public StudentScoreView(StudentScoreController controller, DataSource dataSource) {
        this.controller = controller;
        this.dataSource = dataSource;
    }

    public void run(int studentId) {
        this.studentId = studentId;
        controller.setStudentId(this.studentId);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("学生密码视图菜单:");
            System.out.println("1. 显示成绩");
            System.out.println("2. 返回主菜单");
            System.out.print("请选择: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.displayGrade();
                    break;
                case 2:
                    exit = true;
                    System.out.println("返回主菜单");
                    break;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }
}
