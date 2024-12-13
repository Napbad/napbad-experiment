package org.napbad.score.view;


import org.napbad.score.controller.AdminController;
import org.napbad.score.controller.StudentController;

import java.io.Console;
import java.util.Scanner;

public class LoginView {

    private final MainView mainView;
    private final AdminController adminController;
    private final StudentController studentController;
    private final StudentScoreView studentScoreView;

    public LoginView(MainView mainView, AdminController adminController, StudentController studentController, StudentScoreView studentScoreView) {
        this.mainView = mainView;
        this.adminController = adminController;
        this.studentController = studentController;
        this.studentScoreView = studentScoreView;
    }

    public void run() {

        while (true){
            System.out.println("欢迎使用学生成绩管理系统");
            System.out.println("1. 管理员登录");
            System.out.println("2. 学生登录");
            System.out.println("3. 退出");
            System.out.print("请选择: ");
            Scanner scanner = new Scanner(System.in);
            Console console = System.console();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("请输入管理员ID：");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("请输入管理员密码：");
                    String passwd = scanner.nextLine();
                    if (adminController.login(id, passwd)) {
                        System.out.println("管理员登录成功");
                        mainView.run();
                    } else {
                        System.out.println("管理员登录失败");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("请输入学生ID：");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("请输入学生密码：");
                    String studentPasswd = scanner.nextLine();
                    if (studentController.login(studentId, studentPasswd)) {
                        System.out.println("学生登录成功");
                        studentScoreView.run(studentId);
                    } else {
                        System.out.println("学生登录失败");
                        break;
                    }
                    break;
                case 3:
                    System.out.println("退出程序");
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }
}
